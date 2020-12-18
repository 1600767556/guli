package com.ssm.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/8 10:42
 */


@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.ssm"})
@MapperScan("com.ssm.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
