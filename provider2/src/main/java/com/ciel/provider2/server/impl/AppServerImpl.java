package com.ciel.provider2.server.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciel.mapper.AppCrud;
import com.ciel.pojo.App;
import com.ciel.service.AppServicer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@com.alibaba.dubbo.config.annotation.Service(version = "1.0")  //暴露服务,声明版本
@Service
public class AppServerImpl extends ServiceImpl<AppCrud, App> implements AppServicer {

    @Override
    @HystrixCommand
    public List<App> list() {

        double random = Math.random();
        if(random>0.5){
            throw new RuntimeException("fuck-------------");
        }

        List<App> list = super.list();

        App a = new App();
        a.setName("p222222222222222222222");

        list.add(a);

        return list;
    }

    @Override
    public Page<App> myPage(Page page) {
        QueryWrapper<App> wrapper = new QueryWrapper<>();
        wrapper.ne("app.name","99");
        wrapper.orderByAsc("user.name");

        Page<App> appIPage1 = baseMapper.myPage(page, wrapper);
        appIPage1.getRecords().get(0).setName("P2");
        return  appIPage1;
    }

    @Override
    public List<App> myApp(String name) {
        return null;
    }

    @Override
    public List<App> chang(App app) {
        return null;
    }

    @Override
    public int remove(String id) {
        return 0;
    }

    @Override
    public App getApp(String name) {
        return null;
    }
}
