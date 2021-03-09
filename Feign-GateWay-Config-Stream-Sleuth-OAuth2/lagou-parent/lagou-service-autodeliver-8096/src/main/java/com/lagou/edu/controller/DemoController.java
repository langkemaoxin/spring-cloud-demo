package com.lagou.edu.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/test")
    public String findResumeOpenState() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return "demo/test!";
    }
}
