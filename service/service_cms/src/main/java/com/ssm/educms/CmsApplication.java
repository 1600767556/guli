package com.ssm.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/3 15:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.ssm")
@MapperScan("com.ssm.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
