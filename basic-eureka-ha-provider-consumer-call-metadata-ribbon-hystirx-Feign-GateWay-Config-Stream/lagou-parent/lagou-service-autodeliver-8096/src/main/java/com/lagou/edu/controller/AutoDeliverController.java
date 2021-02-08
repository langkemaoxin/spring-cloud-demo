package com.lagou.edu.controller;

import com.lagou.edu.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

    @Autowired
    private ResumeServiceFeignClient client;

    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId){

//        String url="http://lagou-service-resume/resume/openstate/" + userId;
//
//        Integer forObject = restTemplate.getForObject(url ,Integer.class);
//
//        return forObject;

        return client.findResumeOpenState(userId);
    }

}
