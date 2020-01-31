package com.ciel.provider.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ciel.mapper.AppCrud;
import com.ciel.pojo.App;
import com.ciel.provider.config.Ciel;
import com.ciel.service.AppServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@com.alibaba.dubbo.config.annotation.Service(version = "1.0")  //暴露服务,声明版本
@Service
@CacheConfig(cacheNames = "myApN",cacheManager = "cacheManagerJSON") //指定缓存通用配置; cacheManager指定使用哪一个缓存管理器
@Transactional
public class AppServerImpl extends ServiceImpl<AppCrud, App> implements AppServicer {

    @Autowired
    private Ciel ciel;

    @Autowired
    @Qualifier("cacheManagerJSON")
    private CacheManager cacheManagerJSON;

    @Override
    /**
     * 开启缓存,
     * cacheName/value 把返回结果放在哪几个缓存中, 比如放在 user缓存 app缓存
     * key缓存的key,默认方法参数;可以使用el表达式,使用方法名称 ,key = "#root.method.name";和keyGenerator2选1, KeyGenerator是自定义的key生成器(keyGenerator = "autoGenMy")
     *  #root.target 当前被调用对象; #root.targetClass 当前被调用对象class #root.args[0] 当前方法参数数组
     *condition 取出参数,当符合条件才会缓存, 可以使用el表达式,#a0第一个参数
     *unless : 符合条件否定缓存,可以使用el表达式, 返回结果为空 result==null
     * sync :是否异步模式
     *
     * @Cacheable 标注的方法会在执行前检查缓存中有没有这个数据,默认按照参数的值作为key查找,如果没有就执行方法
     */
    @Cacheable(value = "myApN",unless = "#result==null",condition = "#a0=='xia'",key="'xia1'")
    public List<App> myApp(String name) {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<App> list = baseMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    /**
     * 先调用方法(一定会执行),然后把返回结果更新缓存数据; key要和查询的方法的key相同,也就是覆盖另一个方法的缓存
     */
    @CachePut(value = "myApN",key="'xia1'")
    public List<App> chang(App app){
        baseMapper.updateById(app);

        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","xia");
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * key 指定要清楚的缓存; allEntries = true 删除myApN下的所有缓存;
     * beforeInvocation: 是否在方法执行之前清楚缓存
     */
    @CacheEvict(value = "myApN",key ="'xia1'")
    @Override
    public int remove(String id) {

        Cache myApN = cacheManagerJSON.getCache("myApN");
        App app = new App();app.setName("xiapeixin");
        myApN.put("xiapeixin",app);  //手动缓存
        return 0;
    }

    @Override
    /**
     * 可以可以定义多个key,其他方法用这些key就可以取查缓存了;
     */
    @Caching(
        cacheable = {@Cacheable(value ="myApN",key = "#name")},
         put = {@CachePut(value = "myApN",key = "#result.id"),
                @CachePut(value = "myApN",key = "#result.id")
        }
    )
    public App getApp(String name){
        return baseMapper.selectByName("xia");
    }


    @Override
    public List<App> list() {
        List<App> list = super.list();
        App a = new App();
        a.setName("p1111111111111111111111");
        list.add(a);
        return list;
    }

    @Override
    public Page<App> myPage(Page page) {

        QueryWrapper<App> wrapper = new QueryWrapper<>();
        wrapper.ne("app.name","99");
        wrapper.orderByAsc("user.name");

        Page<App> appIPage1 = baseMapper.myPage(page, wrapper);
        appIPage1.getRecords().get(0).setName("P1");
//        UpdateWrapper<App> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("name","99");
//        updateWrapper.eq("id","55");
//        baseMapper.myChange(updateWrapper);

        return appIPage1;
    }
    
}
