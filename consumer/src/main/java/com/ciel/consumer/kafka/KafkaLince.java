package com.ciel.consumer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaLince {


    public static Logger log = LoggerFactory.getLogger(KafkaLince.class);

    @KafkaListener(topics = "ciel")
    public void onMessage(String message){
        System.out.println("SHOU DAO XIAO XI:"+message);
        log.info(message);
    }
}
