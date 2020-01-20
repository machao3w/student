package com.machao.student.social.qq.connect;

import com.machao.student.social.qq.api.QQ;
import com.machao.student.social.qq.api.imp.QQImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * author: mc
 * date: 2020/1/20 10:15
 */

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";


    public QQServiceProvider(String appId, String appSecret ) {
        super(new OAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String s) {
        return new QQImpl(s,appId);
    }
}
