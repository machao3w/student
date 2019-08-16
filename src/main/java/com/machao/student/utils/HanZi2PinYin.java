package com.machao.student.utils;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class HanZi2PinYin {

    public String changeToTonePinYin(String pinYinStr){

        String tempStr = null;

        try
        {
            tempStr =  PinyinHelper.convertToPinyinString(pinYinStr, " ", PinyinFormat.WITHOUT_TONE);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return tempStr;

    }

}
