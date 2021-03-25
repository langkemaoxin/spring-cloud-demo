package com.lagou.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "lagou-service-resume", path = "/resume")
public interface ResumeServiceFeignClient {

    @GetMapping("/openstate/{userId}")
    Integer findResumeOpenState(@PathVariable(value = "userId") Long userId);
}
