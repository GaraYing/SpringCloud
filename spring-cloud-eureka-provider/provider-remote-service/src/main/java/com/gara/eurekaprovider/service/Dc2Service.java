package com.gara.eurekaprovider.service;

import com.gara.eurekaprovider.config.FeignConfiguration;
import com.gara.eurekaprovider.service.factory.HystrixClientFallbackFactory;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:42
 * @Version: 1.0
 **/
@FeignClient(name = "spring-cloud-eureka-provider", configuration = FeignConfiguration.class ,fallbackFactory = HystrixClientFallbackFactory.class)
public interface Dc2Service {

    @RequestLine("GET /provider/dc")
    String consumer();
}
