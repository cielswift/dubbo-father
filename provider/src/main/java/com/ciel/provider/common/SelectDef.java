package com.ciel.provider.common;

import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class SelectDef implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Other3.class);
        registry.registerBeanDefinition("rectangle", rootBeanDefinition);


//        从容器中尝试获取Cat和Dog，如果两者都存在，那么就向容器中注入Pig类。有条件，有选择性的向容器中注入Bean

        // 获取容器中已经存在的Bean的名字
//        boolean definition1 = registry.containsBeanDefinition("com.nmys.story.springCore.springioc.importBean.Cat");
//        boolean definition2 = registry.containsBeanDefinition("com.nmys.story.springCore.springioc.importBean.Dog");
//
//
//        if (definition1 && definition2) {
//            // 将需要放入容器中的Bean用RootBeanDefinition来包装一下。
//            RootBeanDefinition beanDefinition = new RootBeanDefinition(Other3.class);
//            // 向容器中注册这个Bean，并给定一个名字。
//            registry.registerBeanDefinition("pig", beanDefinition);
    }

}
