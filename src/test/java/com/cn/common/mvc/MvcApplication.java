package com.cn.common.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.cn.common.mvc")
public class MvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(MvcApplication.class);

    }
}
