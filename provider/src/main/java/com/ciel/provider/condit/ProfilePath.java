package com.ciel.provider.condit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfilePath { //指定环境

    @Bean
    @Profile("dev") //加入环境标识,只有环境被激活才能注册,也可写在类上,作用整个配置类
    public Other5 other5d(){
        Other5 other5 = new Other5();
        other5.setName("开发环境");
        return other5;
    }

    @Bean
    @Profile("prod")
    public Other5 other5p(){
        Other5 other5 = new Other5();
        other5.setName("生成环境");
        return other5;
    }


    @Bean
    @Profile("test")
    public Other5 other5t(){
        Other5 other5 = new Other5();
        other5.setName("测试环境");
        return other5;
    }
}
