package com.whkj.spikes.bootstrap;

import com.whkj.spikes.bo.MyUser;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{MyUser.class.getName()};
//        return new String[0];
    }
}
