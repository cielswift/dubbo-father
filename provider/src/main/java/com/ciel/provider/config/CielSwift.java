package com.ciel.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:xiapeixin.properties"})
@ConfigurationProperties(prefix = "xia")
@Component
@Data
public class CielSwift {

    private String name;
    private Integer age;
    private Double length;
    private Boolean sex;
    private String msg;

}
