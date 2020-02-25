package com.ciel.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.ciel.cache.RedisMybatisCache;
import com.ciel.provider.common.Other2;
import com.ciel.provider.common.SelectDef;
import com.ciel.provider.common.SelectImp;
import com.ciel.test.Contr;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo(scanBasePackages = {"com.ciel.provider.server.impl"}) //开启基于注解的dubbo,扫描服务实现
//@ImportResource  //导入一个资源配置文件,可以是duboo的
//@DubboComponentScan("com.ciel.provider.server.impl") //扫描dubbo服务实现的注解

@EnableCaching   //开启基于注解的缓存
@EnableRabbit  //开启基于注解的消息队列
@EnableScheduling //开启定时任务,
@EnableAsync //开启异步注解

/**
 * 开启基于注解的aop
 * exposeProxy=true 表示通过aop框架暴露该代理对象，aopContext能够访问;
 *      然后就可以方法里获取当前类的代理对象;
 *
 *          private HelloServiceImpl getHelloServiceImpl() {
 *           return AopContext.currentProxy() != null ? (HelloServiceImpl) AopContext.currentProxy() : this;
 *          }
 *
 *  proxyTargetClass=true 使用cglib进行代理
 */
@EnableAspectJAutoProxy

/**
 * @Transactional 注解应该只被应用到 public 方法上

 * 默认情况下，只有来自外部的方法调用才会被AOP代理捕获，
 * 也就是，类内部方法调用本类内部的其他方法并不会引起事务行为，即使被调用方法使用@Transactional注解进行修饰。
 *
 * 开启事务,order指定aop的执行顺序,在其他aop(cache)之前执行;
 */
@EnableTransactionManagement(order = Ordered.HIGHEST_PRECEDENCE)


@SpringBootApplication
@MapperScan("com.ciel.mapper")

@ImportResource(locations = "classpath:app-other.xml") //导入其他配置文件
@Import({SelectImp.class, Other2.class, SelectDef.class, Contr.class}) //导入其他bean,或者配置培类

//@ImportAutoConfiguration(XiaConfig.class)  //导入其他不在扫描中的配置类


//Boot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，
// 将这些值作为自动配置类导入到容器中，自动配置类就生效

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

//#  @Autowired
//#  private AutowireCapableBeanFactory acbf; spring的上下文对象,可以直接获取bean


 //   @Configurable(preConstruction = true) //这个注解的作用是：告诉Spring在构造函数运行之前将依赖注入到对象中
    //这就就算当前这个对象是new出来的, 内部的字段也能使用@Autowired自动注入了; 需要aspectj 和启动aspectjAOP;

    /**
     * 上下文环境
     */
//    @Autowired
//    protected ConfigurableApplicationContext context;
    //context.getEnvironment().getProperty("org.dromara.hmily.serializer")

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}


