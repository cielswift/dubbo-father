package com.ciel.provider.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.Arrays;


@Configuration
public class KeyConfig {

    @Bean
    public KeyGenerator autoGenMy(){ //定义缓存 key 的生成策略
        KeyGenerator keyGenerator =
                (target, method, params) -> method.getName()+"["+ Arrays.asList(params).toString()+"]-xiapeixin";

        return keyGenerator;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
