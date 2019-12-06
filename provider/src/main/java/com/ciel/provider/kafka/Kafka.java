package com.ciel.provider.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
public class Kafka {

    //一个组里的消费者不能消费同一个分区的数据

    // 启动 .\bin\windows\kafka-server-start.bat .\config\server.properties
    // 创建主题 .\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic 主题名
    //查看主题 .\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --list
    //启动生产 .\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic 主题名
    //启动消费 .\bin\windows\kafka-console-consumer.bat --zookeeper localhost:2181 --topic 主题名

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    public boolean send() throws JsonProcessingException {

        KafkaMsg message = new KafkaMsg();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setDateTime(LocalDateTime.now());

        kafkaTemplate.send("ciel",new ObjectMapper().writeValueAsString(message));
        return true;
    }


}
