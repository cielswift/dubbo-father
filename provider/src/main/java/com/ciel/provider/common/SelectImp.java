package com.ciel.provider.common;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class SelectImp implements ImportSelector {
    //导入N 个 Bea
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.ciel.provider.common.Other1"};

    }

}
