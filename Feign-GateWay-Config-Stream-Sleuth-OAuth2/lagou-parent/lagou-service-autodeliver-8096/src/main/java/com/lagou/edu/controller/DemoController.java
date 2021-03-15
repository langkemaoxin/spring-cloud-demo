package com.lagou.edu.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/test")
    public String findResumeOpenState() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();

        Object decodedDetails = ((OAuth2AuthenticationDetails) details).getDecodedDetails();

        Map<String, String> stringMap = (Map<String, String>) decodedDetails;

        String clientIp = stringMap.get("clientIp");

        System.out.println(clientIp);

        return "demo/test!";
    }
}
