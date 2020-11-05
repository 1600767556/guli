package com.ssm.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/3 15:01
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.ssm")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
