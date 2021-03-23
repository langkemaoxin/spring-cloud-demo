package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class StreamConsumerApplication9092 {
    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerApplication9092.class,args);
    }
}
