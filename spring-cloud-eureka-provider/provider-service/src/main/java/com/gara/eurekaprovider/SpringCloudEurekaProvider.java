package com.gara.eurekaprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.gara")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.gara")
@EnableHystrix
// 相当于@EnableHystrix + springCloud 相关功能
@EnableCircuitBreaker
@EnableEurekaClient
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
