package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

    @Autowired
    private RestTemplate restTemplate;

    //http://localhost:8090/autodeliver/checkState/1545133
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId){
        //调用远程服务--> 简历微服务

        //直接拿到结果
        Integer forObject = restTemplate.getForObject("http://localhost:8080/resume/openstate/" + userId, Integer.class);

        //拿到响应实体
        //restTemplate.getForEntity();
        //restTemplate.postForObject();


        return forObject;

    }
}
