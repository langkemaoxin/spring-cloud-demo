package com.lagou.edu.controller;

import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;

    //http://localhost:8080/resume/openstate/1545133
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("===》》》》》》》》》》》》我是8080");
        return port;
    }

}
