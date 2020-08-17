package com.gara.sericefeign.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ImportConfigSelector.class)
public class ImportConfig {

    public ImportConfig() {
        System.out.println("===========" + ImportConfig.class.getName());
        System.out.println("===========" + ImportConfig.class.getSimpleName());
    }
}
