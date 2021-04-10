package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
@EnableDiscoveryClient //开启注册中心客户端通用型
public class LagouResumeApplication8084 {
    public static void main(String[] args) {
        SpringApplication.run(LagouResumeApplication8084.class, args);
    }
}
