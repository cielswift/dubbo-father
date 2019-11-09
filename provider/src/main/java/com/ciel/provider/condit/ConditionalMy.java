package com.ciel.provider.condit;

import com.ciel.provider.config.Ciel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

@Configuration
@Conditional(ConditionWindow.class) //返回true才会生效
@AutoConfigureAfter(OtherConfig5.class)  //OtherConfig5加载完后,再加载本类配置

@EnableConfigurationProperties(Ciel.class)  //使@ConfigurationProperties 注解的类生效。
//如果一个配置类只配置@ConfigurationProperties注解,而没有使用@Component，那么在IOC容器中是获取不到properties配置文件转化的bean;
// 说白了 @EnableConfigurationProperties 相当于把使用 @ConfigurationProperties 的类进行了一次注入。
public class ConditionalMy {

    @Bean
    @Lazy
    @Primary //默认
    @Qualifier("other4")
    public Other4 other4(){
        return new Other4();
    }

    //把需要加载的自动配置类,配置在META-INF/spring-factories/下
//    org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
//    com.alibaba.boot.dubbo.autoconfigure.DubboAutoConfiguration
}

//@ConditionalOnClass ： classpath中存在该类时起效
//@ConditionalOnMissingClass ： classpath中不存在该类时起效
//@ConditionalOnBean ： DI容器中存在该类型Bean时起效
//@ConditionalOnMissingBean ： DI容器中不存在该类型Bean时起效
//@ConditionalOnSingleCandidate ： DI容器中该类型Bean只有一个或@Primary的只有一个时起效
//@ConditionalOnExpression ： SpEL表达式结果为true时
//@ConditionalOnProperty ： 参数设置或者值一致时起效
//@ConditionalOnResource ： 指定的文件存在时起效
//@ConditionalOnJndi ： 指定的JNDI存在时起效
//@ConditionalOnJava ： 指定的Java版本存在时起效
//@ConditionalOnWebApplication ： Web应用环境下起效
//@ConditionalOnNotWebApplication ： 非Web应用环境下起效
