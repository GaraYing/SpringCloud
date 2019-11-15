package com.gara.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {

    @Value("${my.name}")
    private String myName;

    @GetMapping("/my-name")
    public String getName(){
        return myName;
    }
}
