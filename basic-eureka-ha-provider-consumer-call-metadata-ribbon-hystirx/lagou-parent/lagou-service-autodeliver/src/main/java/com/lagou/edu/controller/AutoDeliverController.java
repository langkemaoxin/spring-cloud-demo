package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

    @Autowired
    private RestTemplate restTemplate;

    //http://localhost:8090/autodeliver/checkState/1545133
    @GetMapping("/old/checkState/{userId}")
    public Integer findResumeOpenState_old(@PathVariable Long userId){
        //调用远程服务--> 简历微服务

        //直接拿到结果
        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/openstate/" + userId, Integer.class);

        //拿到响应实体
        //restTemplate.getForEntity();
        //restTemplate.postForObject();


        return forObject;

    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/old2/checkState/{userId}")
    public Integer findResumeOpenState_old2(@PathVariable Long userId){

        //1. 从Eureka Server中获取 服务的实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");

        //2、如果有多个实例，选择一个使用
        ServiceInstance serviceInstance = instances.get(0);

        //3、从元数据信息获取host port
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url="http://"+host+":"+port+"/resume/openstate/" + userId;

        System.out.println("Eureka服务信息>>>>>>>>>>>>>>>"+url);


        Integer forObject = restTemplate.getForObject(url ,Integer.class);

        return forObject;
    }

    //http://localhost:8090/autodeliver/checkState/1545133
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId){

        String url="http://lagou-service-resume/resume/openstate/" + userId;

        Integer forObject = restTemplate.getForObject(url ,Integer.class);

        return forObject;
    }
}
