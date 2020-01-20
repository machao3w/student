package com.machao.student.social.qq.api.imp;

import com.alibaba.fastjson.JSON;
import com.machao.student.social.qq.api.QQ;
import com.machao.student.social.qq.model.QQInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;


/**
 * author: mc
 * date: 2020/1/19 17:30
 */

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ  {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%S";

    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%S&openid=%s";

    private String appId;

    private String openId;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result,"\"openid\":","}");
    }

    @Override
    public QQInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);
        System.out.println(result);
        QQInfo qqInfo = JSON.parseObject(result,QQInfo.class);

        return null;
    }
}
