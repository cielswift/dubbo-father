package com.ciel.provider2;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableDubbo(scanBasePackages = {"com.ciel.provider2.server.impl"})
@SpringBootApplication
@MapperScan("com.ciel.mapper")
@EnableHystrix  //开启容错机制
public class Provider2Application {

    public static void main(String[] args) {
        SpringApplication.run(Provider2Application.class, args);
    }

}
