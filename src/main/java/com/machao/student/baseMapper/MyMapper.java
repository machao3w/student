package com.machao.student.baseMapper;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

public interface MyMapper<T> extends BaseMapper<T>, ExampleMapper<T> {

}
