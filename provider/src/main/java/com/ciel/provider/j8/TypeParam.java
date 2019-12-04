package com.ciel.provider.j8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE_PARAMETER) //注解可以放在泛型上
@Target(ElementType.TYPE_USE) //注解可以放在任何地方上
public @interface TypeParam {

}
