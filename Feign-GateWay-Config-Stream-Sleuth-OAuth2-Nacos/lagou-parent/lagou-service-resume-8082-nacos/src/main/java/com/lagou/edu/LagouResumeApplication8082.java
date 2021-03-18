package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
//@EnableEurekaClient //Eureka独有 效果和EnableDiscoveryClient一样
@EnableDiscoveryClient //开启注册中心客户端通用型
//说明：从SpringCloud的 Edgware版本开始，不加注解也OK
public class LagouResumeApplication8082 {
    public static void main(String[] args) {
        SpringApplication.run(LagouResumeApplication8082.class, args);
    }
}
