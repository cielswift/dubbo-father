package com.ciel.provider.config;

import com.alibaba.dubbo.config.*;
import com.ciel.provider.server.impl.AppServerImpl;
import com.ciel.service.AppServicer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class DubboConfig { //dubbo配置

    @Bean
    @Lazy
    @Primary
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("app-provider");
        return applicationConfig;
    }

    @Bean
    @Lazy
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:21810,127.0.0.1:21811,127.0.0.1:21812");
        registryConfig.setCheck(false);
        return registryConfig;
    }

    @Bean
    @Lazy
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
    }

    @Bean
    @Lazy
    public MonitorConfig monitorConfig(){
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }

    @Bean
    @Lazy
    public ServiceConfig<AppServicer> servicerServiceConfig(AppServerImpl appServerImpl){
        ServiceConfig<AppServicer> servicerServiceConfig = new ServiceConfig<>();
        servicerServiceConfig.setInterface(AppServicer.class);
        servicerServiceConfig.setRef(appServerImpl);
        servicerServiceConfig.setTimeout(3000);

        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("list");
        methodConfig.setTimeout(5000);

        List<MethodConfig> list = new LinkedList<>();
        list.add(methodConfig);

        servicerServiceConfig.setMethods(list);

        return servicerServiceConfig;
    }

    @Bean
    @Lazy
    public ProviderConfig providerConfig(){
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setVersion("1.0");
        return providerConfig;
    }


}
