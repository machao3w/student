package com.machao.student.social.qq.config;

import com.machao.student.social.qq.connect.QQConnectionFactorty;
import com.machao.student.utils.Constants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * author: mc
 * date: 2020/1/20 15:09
 */
@Configuration
@ConditionalOnProperty(prefix = "student", name = "app-id")
public class QQSocialConfig extends SocialConfigurerAdapter {

    private ConnectionFactory createConnectionFactory(){
        return new QQConnectionFactorty(Constants.QQ_PROVIDER_ID,Constants.APP_ID,Constants.APP_SECRET);
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(this.createConnectionFactory());
    }
}
