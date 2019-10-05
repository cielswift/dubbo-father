package com.ciel.provider.condit;

import com.ciel.provider.config.Ciel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
@Conditional(ConditionWindow.class) //返回true才会生效
@AutoConfigureAfter(OtherConfig5.class)  //OtherConfig5加载完后,再加载本类配置

@EnableConfigurationProperties(Ciel.class)  //使@ConfigurationProperties 注解的类生效。
//如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，那么在IOC容器中是获取不到properties 配置文件转化的bean。
// 说白了 @EnableConfigurationProperties 相当于把使用 @ConfigurationProperties 的类进行了一次注入。
public class ConditionalMy {

    @Bean
    @Lazy
    @Primary
    @Qualifier("other4")
    public Other4 other4(){
        return new Other4();
    }

    //把需要加载的自动配置类,配置在META-INF/spring-factories/下
//    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
//    com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration
}
