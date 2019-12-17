package com.ciel.provider.autoaction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1) //规定所有CommandLineRunner实例的运行顺序,加载执行的优先级根据value值从小到大执行。
public class MyStartupRunner1 implements CommandLineRunner {

    //启动自执行
//在Spring Boot启动的过程中进行一些额外的操作

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    }

}
