package com.crm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//configure and then run project
//runs Atomatically
public class ConfigClass {
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();

    }
}
