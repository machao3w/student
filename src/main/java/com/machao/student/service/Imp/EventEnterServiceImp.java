package com.machao.student.service.Imp;

import com.machao.student.Exception.RegisterException;
import com.machao.student.dao.EventEnterMapper;
import com.machao.student.dao.EventMapper;
import com.machao.student.dao.StudentMapper;
import com.machao.student.entity.Event;
import com.machao.student.entity.EventEnter;
import com.machao.student.entity.Student;
import com.machao.student.service.EventEnterService;
import com.machao.student.service.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


@Service
public class EventEnterServiceImp implements EventEnterService {

    private final static int TIMEOUT = 10*1000;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private EventEnterMapper eventEnterMapper;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public synchronized void register(EventEnter eventEnter) {
        //通过报名表获取活动表id
        Integer id = eventEnter.getEventId();
        Event event = eventMapper.selectByPrimaryKey(id);
        int limitQuantity = event.getLimitQuantity();
        if(limitQuantity == 0){
            throw new RegisterException(200,"报名已满");
        }else{
            eventEnterMapper.insert(eventEnter);
            limitQuantity = limitQuantity -1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.setLimitQuantity(limitQuantity);
            eventMapper.updateByPrimaryKeySelective(event);
        }
    }

    @Override
    public boolean checkNumer(String number) {
        //通过学号检查是否是该学校学生，是否已报名
        Example example = new Example(EventEnter.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("number",number);
        int count = eventEnterMapper.selectCountByExample(example);
        Student student = studentMapper.selectByPrimaryKey(number);
        return count == 0 && student != null;
    }

    @Transactional
    public void register0(EventEnter eventEnter) {

        long time = System.currentTimeMillis()+TIMEOUT;
        Integer id = eventEnter.getEventId();
        //枷锁
        if(!redisLock.lock(String.valueOf(id),String.valueOf(time))){
            throw new RegisterException(101,"报名人数过多");
        }

        Event event = eventMapper.selectByPrimaryKey(id);
        int limitQuantity = event.getLimitQuantity();
        if(limitQuantity == 0){
            throw new RegisterException(200,"报名已满");
        }else{
            eventEnterMapper.insert(eventEnter);
            limitQuantity = limitQuantity -1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            event.setLimitQuantity(limitQuantity);
            eventMapper.updateByPrimaryKeySelective(event);
        }
        eventEnterMapper.insert(eventEnter);

        //解锁
        redisLock.unlock(String.valueOf(id),String.valueOf(time));
    }


}
