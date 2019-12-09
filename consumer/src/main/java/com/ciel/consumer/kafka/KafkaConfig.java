package com.ciel.consumer.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
    kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setPollTimeout(1500);
        //配置手动提交offset
        factory.getContainerProperties().setAckMode((ContainerProperties.AckMode.MANUAL));

        return factory;
    }
}
//    RECORD
//            每处理一条commit一次
//    BATCH(默认)
//每次poll的时候批量提交一次，频率取决于每次poll的调用频率
//        TIME
//        每次间隔ackTime的时间去commit
//        COUNT
//        累积达到ackCount次的ack去commit
//        COUNT_TIME
//        ackTime或ackCount哪个条件先满足，就commit
//        MANUAL
//        listener负责ack，但是背后也是批量上去
//        MANUAL_IMMEDIATE
//        listner负责ack，每调用一次，就立即commit