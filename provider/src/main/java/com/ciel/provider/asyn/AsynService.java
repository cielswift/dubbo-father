package com.ciel.provider.asyn;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

@Service
public class AsynService {


    /**
     * 异步回调消息方法
     *
     * @return 字符串
     */
    @Async
    public Future<String> returnMessage() {
        System.out.println(Thread.currentThread().getName());
        String message = "夏培鑫 异步返回值 阻塞";
        return new AsyncResult<>(message);
    }

    /**
     * 异步回调消息方法
     *
     * @return 字符串
     */
    @Async
    public ListenableFuture<String> returnMsg() {
        System.out.println(Thread.currentThread().getName());
        String message = "夏培鑫 异步返回值 非阻塞";
        return new AsyncResult<>(message);
    }


}