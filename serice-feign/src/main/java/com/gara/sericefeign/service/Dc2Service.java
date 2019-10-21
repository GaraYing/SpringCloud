package com.gara.sericefeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:42
 * @Version: 1.0
 **/
@Component
@FeignClient(name = "spring-cloud-eureka-provider",fallbackFactory = HystrixClientFallbackFactory.class)
public interface Dc2Service {

    @GetMapping("/dc")
    String consumer();
}
