package com.lagou.bussiness.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("lagou-storage-service")
public interface StorageServiceFeign {

    @GetMapping("/storage/decrease")
    public void decrease(@RequestParam("goodsId") Integer goodsId, @RequestParam("quantity") Integer quantity);
}
