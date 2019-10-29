package com.gara.springcloudconfigclient.controller;

import com.gara.springcloudconfigclient.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/my-name")
    public String getName(){
//        return "succ";
        return homeService.testGetName();
    }
}
