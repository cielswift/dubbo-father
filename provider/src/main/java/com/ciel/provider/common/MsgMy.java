package com.ciel.provider.common;

import lombok.Data;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

@Component
@Data
public class MsgMy {

    private String mys = "44";

    @RabbitListener(queues = "xin") //监听某个队列
    public void receive(Message message){

       // DeferredResult<String>
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
        System.out.println(message.getClass());
    }



}
