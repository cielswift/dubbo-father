package com.ciel.consumer.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class KafkaLince {


    public static Logger log = LoggerFactory.getLogger(KafkaLince.class);

    @KafkaListener(topics = "xiapeixin",
            topicPartitions=@TopicPartition(topic = "xiapeixin",partitions = "0"
            )) //监听哪一个分区
    public void zeroMessage(ConsumerRecord<?, ?> cr, Acknowledgment ack,String message){

        log.info("0号分区的数据:"+message);

        //ack.acknowledge(); 手动提交
    }


    @KafkaListener(topics = "xiapeixin",
            topicPartitions=@TopicPartition(topic = "xiapeixin",partitions = "1")) //监听哪一个分区
    public void oneMessage(ConsumerRecord<?, ?> cr,Acknowledgment ack,String message){

        log.info("1号分区的数据:"+message);
    }


}
