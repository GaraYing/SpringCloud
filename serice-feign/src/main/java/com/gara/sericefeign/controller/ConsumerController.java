package com.gara.sericefeign.controller;

import com.gara.sericefeign.service.Dc2Service;
import com.gara.sericefeign.service.DcService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:47
 * @Version: 1.0
 **/
@RestController
public class ConsumerController {

    @Autowired
    private DcService dcService;
    @Autowired
    private Dc2Service dc2Service;

    @HystrixCommand(fallbackMethod = "defaultMethod")
    @GetMapping("feign/consumer")
    public String testFeign(){
        return dcService.consumer();
    }

    private String defaultMethod() {
        return "HystrixCommand Service Failed";
    }

    @GetMapping("feign/consumer2")
    public String testFeign2(){
        return dc2Service.consumer();
    }
}

