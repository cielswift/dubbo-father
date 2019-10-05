package com.ciel.provider.controller;

import com.ciel.pojo.App;
import com.ciel.service.AppServicer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a2")
public class AppRabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin admin;

    //direct 点对点路由器, 只会发送到下属的路由键相同的队列;
    //fanout 广播模式, 发送到下属所有的队列
    //topic 模糊匹配,发送到符合规则的 路由键的队列, 比如消息的路由键是 a.b , 队列的路由键是a.# ; #一个或多个,*0个或一个

    @GetMapping("/a2")
    public void sendRabbitMQ(){

        App app = new App();
        app.setName("xia pei xin 202");
            //交换器,路由键,消息
        //rabbitTemplate.convertAndSend("xiapeixin3","xia.b",app);

        rabbitTemplate.convertAndSend("xiapeixin2","",app); //广播
    }

    @GetMapping("/a3")
    public void receiveRabbitMQ(){

        Object xin = rabbitTemplate.receiveAndConvert("xin");//队列名称

        System.out.println(xin);
    }

    @GetMapping("/admin")
    public void admin(){
//        admin.declareExchange(new DirectExchange("xiatian"));
//        admin.declareExchange(new FanoutExchange("peitian"));
//        admin.declareExchange(new TopicExchange("xintian"));
//
//        admin.declareQueue(new Queue("nazi1"));
//        admin.declareQueue(new Queue("nazi2"));
//        admin.declareQueue(new Queue("nazi3"));

        Binding binding =
                new Binding("nazi1", Binding.DestinationType.QUEUE, "xiatian", "nazi1", null);
        admin.declareBinding(binding);
    }

    @RequestMapping(value = "/partest",method = RequestMethod.POST)
    public List<App> partest(@RequestBody App app){
        return null;
    }
}
