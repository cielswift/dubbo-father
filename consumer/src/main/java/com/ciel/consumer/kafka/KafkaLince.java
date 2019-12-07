package com.ciel.consumer.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaLince {


    public static Logger log = LoggerFactory.getLogger(KafkaLince.class);

    @KafkaListener(topics = "xiapeixin")
    public void onMessage(String message){



        log.info(message);
    }


}
