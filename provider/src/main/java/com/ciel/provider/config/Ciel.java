package com.ciel.provider.config;

import io.searchbox.annotations.JestId;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Scope("singleton") //单例
@Component
@ConfigurationProperties(prefix = "ciel")//通过前缀自动配置 导入spring-boot-configuration-processor提示
@Validated //注入时校验
@Document(indexName = "xiatian",type = "xia") //索引和类型
public class Ciel implements Serializable{//, BeanPostProcessor {

    //@Lookup //总是返回新的对象,如果@Scope是prototype的; ,只能放在方法上
    //@Lazy //针对单实例bean

    private String name;
    private Integer age;
    private Double price;
    private Boolean sex;

    @Email
    private String email;

    private String rans;

    @Value("#{msgMy.mys}")
    private String custom;

    private List<String> as;
    private Map<String,String> mps;

    @JestId //指定搜索id
    private Integer id;

    /**
     * es防止netty的bug
     * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
     */
    @PostConstruct //初始化方法 ,创建完成属性赋值完成
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }


    @PreDestroy  //销毁方法
    void destroy(){
        System.out.println("aa");
    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(bean);
//        return null;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println(bean);
//        return null;
//    }
}
