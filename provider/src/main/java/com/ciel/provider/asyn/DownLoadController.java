package com.ciel.provider.asyn;

import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController

@AllArgsConstructor
public class DownLoadController {

    protected AsynService service;

    @GetMapping("/page1")
    public String asyncPage1() {

        try {
            System.out.println(Thread.currentThread().getName());

            Future<String> result = service.returnMessage();

            System.out.println(result.get());
            //使用了Future的get方法获取了异步方法的返回值，但是这种获取返回值的方式会阻塞当前线程，也就是说调用了get方法之后，
            // 会等待异步线程执行完毕后才进行下一行代码的执行
        } catch (Exception  e) {
            System.out.println(e);
        }
        return "async";
    }


    @GetMapping("/page2")
    public String asyncPage2() {
        System.out.println(Thread.currentThread().getName());
        ListenableFuture<String> result = service.returnMsg();

        result.addCallback(new SuccessCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("返回的结果是：" + result);
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println(ex);
            }
        });
        return "async";
    }

    //从上面的代码中可以看出，在返回的结果中添加了两个回调，分别是异步处理成功的回调SuccessCallback接口的实现类对象
    // 和异步处理失败发生异常的回调FailureCallback接口的实现类对象。ListenableFuture接口是对Future接口的扩展，支持回调，
    // 有效的避免了线程阻塞问题，也就是说，它会监听Future接口的执行情况，一旦完成，就会调用onSuccess方法进行成功后的处理，一旦发生异常，
    // 就会调用onFailure方法进行异常处理。相比较而言，更加推荐使用ListenableFuture来进行有返回值的异步处理。

    // 对于Java1.8，其实更加推荐使用CompletableFuture或者guava的ListenableFuture，感兴趣的同学可以进行深入研究，他们的处理异步能力会更加强悍。


    @Async
    @RequestMapping("/asynDown")
    public void asyn(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/force-download");
        response.setHeader("Access-Control-Expose-Headers", "FileName");
        //response.setContentLengthLong(900);
        response.setHeader("Content-Disposition", "attachment;filename=sex.avi");
        byte [] fileArr = FileUtils.readFileToByteArray(new File("C:\\Users\\Administrator\\Desktop\\捕获.PNG"));
        OutputStream is = response.getOutputStream();
        is.write(fileArr);
        is.flush();
        is.close();
    }

}