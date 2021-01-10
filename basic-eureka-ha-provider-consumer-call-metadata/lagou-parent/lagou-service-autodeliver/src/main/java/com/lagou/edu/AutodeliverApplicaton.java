package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient //开启注册中心客户端通用型
public class AutodeliverApplicaton {

    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplicaton.class,args);
    }

    //使用Resttemple进行远程调用,先对注入该对象
    @Bean
    public RestTemplate getRestTemplete(){
        return new RestTemplate();
    }
}
