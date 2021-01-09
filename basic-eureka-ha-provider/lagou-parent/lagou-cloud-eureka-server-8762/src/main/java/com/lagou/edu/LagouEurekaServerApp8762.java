package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//声明当前项目为 Eureka服务
@EnableEurekaServer
public class LagouEurekaServerApp8762 {
    public static void main(String[] args) {
        SpringApplication.run(LagouEurekaServerApp8762.class,args);
    }
}
