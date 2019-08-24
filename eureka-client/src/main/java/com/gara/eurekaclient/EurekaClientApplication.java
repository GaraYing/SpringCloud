package com.gara.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    /**
     * 实现自定义配置
     * 1. 实现 PropertySourceLocator
     * 2. spring bean 管理
     * 3. 实现 propertySource
     * 4. 定义并且配置META-INF/spring.properties
     */
    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class MyPropertySourceLocator implements PropertySourceLocator{

        @Override
        public PropertySource<?> locate(Environment environment) {
            Map<String, Object> source = new HashMap<>();
            source.put("server.port", "9090");

            MapPropertySource propertySource = new MapPropertySource("my-property-source", source);
            return propertySource;
        }
    }
}
