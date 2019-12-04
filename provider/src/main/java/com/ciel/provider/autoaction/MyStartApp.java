package com.ciel.provider.autoaction;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyStartApp implements ApplicationRunner { //启动之后的额外操作

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("aaaaaaaaaa");
    }
}
