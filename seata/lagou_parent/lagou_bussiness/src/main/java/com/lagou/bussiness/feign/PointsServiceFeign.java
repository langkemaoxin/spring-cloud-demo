package com.lagou.bussiness.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("lagou-points-service")
public interface PointsServiceFeign {

    @GetMapping("/points/increase")
    public void increase(@RequestParam("username") String username, @RequestParam("points") Integer points);
}
