package com.gara.sericefeign.service;

import com.gara.sericefeign.config.FeignConfiguration;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:42
 * @Version: 1.0
 **/
@Component
@FeignClient(name = "spring-cloud-eureka-provider", configuration = FeignConfiguration.class ,fallbackFactory = HystrixClientFallbackFactory.class)
public interface Dc2Service {

    @RequestLine("GET /dc")
    String consumer();
}
