package com.cn.common.mvc.configuration;

import com.cn.common.mvc.http.ControllerLogAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SystemConfiguration {

    @Bean
    public ControllerLogAdvisor controllerLogAdvisor(){
        return new ControllerLogAdvisor();
    }
}
