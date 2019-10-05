package com.ciel.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ciel.pojo.App;
import com.ciel.service.AppServicer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Reference(check = false,timeout = 3000)  //调用远程服务 //check时是否检查
    //url url = "127.0.0.1:20880"跳过注册中调用  //loadbalance =  负载均衡机制
    private AppServicer appServicer;

    @HystrixCommand(fallbackMethod = "hello")//出错时调用 hello方法
    @RequestMapping("/user")
    public List<App> apps(){

        App app = new App();
        app.setName("eeeeeeeeeeeeeeeeeee");
        appServicer.save(app);  //rpc 调用传参

        appServicer.updateById(app);
        return appServicer.list();
    }

    @RequestMapping("/page")
    public IPage<App> mypage(Page<App> page){

        IPage<App> appIPageR = appServicer.myPage(page);

        return appIPageR;
    }


    public List<App> hello(){
       List<App> list = new ArrayList<>();

       App app = new App();
       app.setName("rrrrrrrr");

       list.add(app);
       return list;
    }

    @RequestMapping(value = "/partest",method = RequestMethod.POST)
    public List<App> partest(@RequestBody App app){


        return null;
    }

}
