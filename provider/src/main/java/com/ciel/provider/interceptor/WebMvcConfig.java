package com.ciel.provider.interceptor;

import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FormContentFilter formContentFilter() {
        return new FormContentFilter();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/xiapeixin").setViewName("/fram/welcome");
        //路径映射到哪一个页面
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorCiel()).addPathPatterns("/**").excludePathPatterns("/error/page/**");
        //拦截的地址和排除的地址
    }

    /** 注册三大组件*/
    @Bean
    @Lazy
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean slb = new ServletRegistrationBean();
        slb.setServlet(new MyServlet());
        slb.setUrlMappings(Arrays.asList(new String[]{"/asd"}));
        return slb;
    }

    @Bean
    @Lazy
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean frb =new FilterRegistrationBean();
        frb.setFilter(new MyFilter());
        frb.setUrlPatterns(Arrays.asList(new String[]{"/*"}));
        return frb;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean slrb = new ServletListenerRegistrationBean();
        slrb.setListener(new MyListener());
        return slrb;
    }

    /**-------------------druid数据源监控----------------------*/
    @Bean
    @Lazy
    public ServletRegistrationBean servletRegistrationBean2(){
        ServletRegistrationBean slb = new ServletRegistrationBean();
        slb.setServlet(new StatViewServlet());
        slb.setUrlMappings(Arrays.asList(new String[]{"/druid/*"}));

        Map<String,String> initParam = new HashMap<>();
        initParam.put("loginUsername","ciel");
        initParam.put("loginPassword","c");

        //白名单：
        initParam.put("allow","127.0.0.1");

        //initParam.put("deny","127.0.0.1");

        slb.setInitParameters(initParam);
        return slb;
    }

    @Bean
    @Lazy
    public FilterRegistrationBean filterRegistrationBean2(){
        FilterRegistrationBean frb =new FilterRegistrationBean();
        frb.setFilter(new WebStatFilter());
        frb.setUrlPatterns(Arrays.asList(new String[]{"/*"}));

        Map<String,String> initParam = new HashMap<>();
        initParam.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        frb.setInitParameters(initParam);
        return frb;
    }

}