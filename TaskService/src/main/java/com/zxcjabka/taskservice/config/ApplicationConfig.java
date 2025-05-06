package com.zxcjabka.taskservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ApplicationConfig {
    @Bean
    public Validator validatorFactory() {
        return new LocalValidatorFactoryBean();
    }
}
