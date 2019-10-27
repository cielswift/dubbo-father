package com.ciel.provider;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.ciel.provider.common.Other2;
import com.ciel.provider.common.SelectDef;
import com.ciel.provider.common.SelectImp;
import com.ciel.provider.condit.TypeF;
import com.ciel.provider.config.Ciel;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.*;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@EnableDubbo(scanBasePackages = {"com.ciel.provider.server.impl"}) //开启基于注解的dubbo,扫描服务实现
//@ImportResource  //导入一个资源配置文件,可以是duboo的
//@DubboComponentScan("com.ciel.provider.server.impl") //扫描dubbo服务实现的注解

@EnableCaching   //开启基于注解的缓存
@EnableRabbit  //开启基于注解的消息队列
@EnableScheduling //开启定时任务,
@EnableAsync //开启异步注解
@SpringBootApplication
@MapperScan("com.ciel.mapper")

@ImportResource(locations = "classpath:app-other.xml") //导入其他配置文件
@Import({SelectImp.class, Other2.class, SelectDef.class}) //导入其他bean,或者配置培类

/*
@ComponentScan(
  value = {"com.ciel"},
  excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, Service.class})},
  includeFilters = {
    @ComponentScan.Filter(type = FilterType.CUSTOM,classes = TypeF.class),
    @ComponentScan.Filter(type = FilterType.REGEX,pattern = {"\\w+"}),
    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = Ciel.class)},
  useDefaultFilters = false
    ) */
        //包扫描,excludeFilters,排除规则是注解,带有@Controller和@Service注解的类
         //       includeFilters,只包含
        //useDefaultFilters 禁用默认规则

//规则:FilterType.ANNOTATION 类上有没有这个注解
//       FilterType.ASSIGNABLE_TYPE,明确指定类
//       FilterType.REGEX,类名满足表达式
//     FilterType.CUSTOM 自定义规则

public class ProviderApplication {

    //  #@PersistenceContext() //通过工厂对象来创建并注入
//
//#  @Autowired
//#  private AutowireCapableBeanFactory acbf; spring的上下文对象,可以直接获取bean

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}


