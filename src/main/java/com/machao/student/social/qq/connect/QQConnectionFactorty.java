package com.machao.student.social.qq.connect;

import com.machao.student.social.qq.adapter.QQAdapter;
import com.machao.student.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * author: mc
 * date: 2020/1/20 10:58
 */

public class QQConnectionFactorty extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactorty(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
    }
}
