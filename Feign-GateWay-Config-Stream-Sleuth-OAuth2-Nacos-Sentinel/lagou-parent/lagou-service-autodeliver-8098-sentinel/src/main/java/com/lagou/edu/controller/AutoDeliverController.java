package com.lagou.edu.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lagou.edu.config.SentinelHandlersClass;
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
    @SentinelResource(value = "findResumeOpenState",
            blockHandlerClass = SentinelHandlersClass.class, blockHandler = "handleException",
            fallbackClass = SentinelHandlersClass.class,fallback = "handleError")
    public Integer findResumeOpenState(@PathVariable Long userId){
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return client.findResumeOpenState(userId);
    }

}
