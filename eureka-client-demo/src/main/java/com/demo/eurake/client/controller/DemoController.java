package com.demo.eurake.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/test")
    public String test(){
        return "test for eureka client";
    }
}
