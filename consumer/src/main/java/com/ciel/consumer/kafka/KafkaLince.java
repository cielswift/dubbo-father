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

    @KafkaListener(topics = "cielswift",groupId = "ciel-group",
            topicPartitions=@TopicPartition(topic = "cielswift",
            partitionOffsets = @PartitionOffset(partition = "0",initialOffset = "0")
            )) //监听哪一个分区
    public void zeroMessage(ConsumerRecord message){

        log.info("==============================================================");
        log.info("0号分区的数据:"+message.offset()+"=="+message.value());
        log.info("==============================================================");

        //ack.acknowledge(); 手动提交
    }

    @KafkaListener(topics = "cielswift",
            topicPartitions=@TopicPartition(topic = "cielswift",
             partitionOffsets = @PartitionOffset(partition = "0",initialOffset = "0")
            )) //监听哪一个分区
    public void oneMessage(ConsumerRecord message){

        log.info("==============================================================");
        log.info("1号分区的数据:"+message.offset()+"=="+message.value());
        log.info("==============================================================");
    }


}
