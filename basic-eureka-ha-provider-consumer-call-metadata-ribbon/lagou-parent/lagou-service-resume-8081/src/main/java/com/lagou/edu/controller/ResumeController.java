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

//    //http://localhost:8080/resume/openstate/1545133
//    @GetMapping("/openstate/{userId}")
//    public Integer findDefaultResumeState(@PathVariable Long userId){
//
//        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
//    }

    @Value("${server.port}")
    private Integer port;

    //http://localhost:8081/resume/openstate/1545133
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId){

        return port;
    }

}
