package com.gara.eurekaprovider;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: GaraYing
 * @create: 2018-09-19 16:28
 **/

@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @ApiOperation(value = "测试")
    @GetMapping("/dc")
    public String dc() {
        String services = "Sevice: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}
