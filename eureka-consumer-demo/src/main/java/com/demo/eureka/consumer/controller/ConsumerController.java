package com.demo.eureka.consumer.controller;

import com.demo.eureka.consumer.feign.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping("/consumer_test")
    public String consumerTest() {
        return eurekaClient.test();
    }
}
