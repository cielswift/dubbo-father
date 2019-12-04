package com.ciel.provider.interceptor;

import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer { //或者继承WebMvcConfigurationSupport

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        // 返回Json时 不展示password字段和salt字段。
        String[] beanProperties = new String[]{"password", "salt"};
        String nonPasswordFilterName = "non-password";
        FilterProvider filterProvider = new SimpleFilterProvider()
                //serializeAllExcept 表示序列化全部，除了指定字段
                //filterOutAllExcept 表示过滤掉全部，除了指定的字段
                .addFilter(nonPasswordFilterName, SimpleBeanPropertyFilter.serializeAllExcept(beanProperties));


        objectMapper.setFilterProvider(filterProvider);
        // 设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        // 设置时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // null值字段不返回
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

//    @Override  //WebMvcConfigurationSupport
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(customJackson2HttpMessageConverter());
//        super.addDefaultHttpMessageConverters(converters);
//    }


    @Bean
    public FormContentFilter formContentFilter() { //注册rest风格url
        return new FormContentFilter();
    }

    @Override //手动添加一个controller
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/xiapeixin").setViewName("/fram/welcome");
        //路径映射到哪一个页面
    }

    @Override  //注册拦截器
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
