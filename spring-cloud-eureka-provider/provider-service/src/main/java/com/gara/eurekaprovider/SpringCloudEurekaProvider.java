package com.gara.eurekaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.gara")
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class SpringCloudEurekaProvider {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaProvider.class, args);
    }

//    @Value("${server.port}")
//    String port;
//
//    @RequestMapping("/hi")
//    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
//        return "hi " + name + " ,i am from port:" + port;
//    }
}
