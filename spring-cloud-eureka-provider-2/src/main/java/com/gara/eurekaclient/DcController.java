package com.gara.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    String port;

    @GetMapping("/dc")
    public String dc() {
        String services = "Sevice: " + discoveryClient.getServices();
        System.out.println(services);
        return services + " from" + port;
    }
}
