package com.gara.springcloudconfigclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-28 17:42
 * @Version: 1.0
 **/
//@Service
public class HomeServiceImpl implements HomeService{

    @Value("${my.name}")
    private String myName;


    @Override
    public String testGetName() {
        return myName;
    }
}
