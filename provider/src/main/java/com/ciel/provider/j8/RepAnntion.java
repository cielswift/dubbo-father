package com.ciel.provider.j8;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(value = RepActions2.class)  //可重复注解,指定容器
public @interface RepAnntion {
    String [] value();
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RepActions2 {
    RepAnntion [] value();  //重复注解的容器
}