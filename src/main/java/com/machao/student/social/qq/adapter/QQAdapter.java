package com.machao.student.social.qq.adapter;

import com.machao.student.social.qq.api.QQ;
import com.machao.student.social.qq.model.QQInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * author: mc
 * date: 2020/1/20 10:31
 */

public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ qq) {
        return true ;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQInfo qqInfo = qq.getUserInfo();
        connectionValues.setDisplayName(qqInfo.getNickname());
        connectionValues.setImageUrl(qqInfo.getFigureurl_qq_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(qqInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
