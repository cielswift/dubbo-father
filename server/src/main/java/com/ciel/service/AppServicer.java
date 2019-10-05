package com.ciel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ciel.pojo.App;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AppServicer extends IService<App> {

    public Page<App> myPage(Page page);

    List<App> myApp(String name);

    List<App> chang(App app);

    int remove(String id);

    public App getApp(String name);


}
