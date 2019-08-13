package com.demo.eureka.consumer.feign;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("eureka-client1")
public interface EurekaClient {

    @LoadBalanced
    @RequestMapping(method = RequestMethod.GET, value = "/test")
    String test();
}
