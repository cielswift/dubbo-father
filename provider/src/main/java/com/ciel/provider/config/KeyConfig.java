package com.ciel.provider.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
