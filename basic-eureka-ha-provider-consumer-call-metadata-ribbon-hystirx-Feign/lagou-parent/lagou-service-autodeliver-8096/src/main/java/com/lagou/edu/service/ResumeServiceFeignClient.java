package com.lagou.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//原来的调用方式
//String url="http://lagou-service-resume/resume/openstate/" + userId;
//表明当前类是一个Feign客户端，value指定该客户端 要请求的服务名称（登记到注册中心上的服务提供者的名称）
@FeignClient(value = "lagou-service-resume")
@RequestMapping("/resume")
public interface ResumeServiceFeignClient {


    //调⽤的请求路径
    //@RequestMapping(value = "/openstate/{userId}",method=  RequestMethod.GET)
    @GetMapping("/openstate/{userId}")
    //这里的value=userID ==> 必须得写上
    public Integer findResumeOpenState(@PathVariable(value = "userId") Long userId);
}
