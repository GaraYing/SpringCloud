package com.gara.eurekaprovider.service;

import com.gara.eurekaprovider.config.FeignConfiguration;
import com.gara.eurekaprovider.service.factory.HystrixClientFallbackFactory;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:42
 * @Version: 1.0
 **/
@FeignClient(name = "spring-cloud-eureka-provider", contextId = "dc2",/* url = "http://192.168.30.68:7777/",*/ configuration = FeignConfiguration.class, fallbackFactory = HystrixClientFallbackFactory.class)
public interface Dc2Service {

    @RequestLine("GET /provider/dc")
    String consumer();

    @RequestLine("GET /provider/customer/{id}")
    Object queryCustomer(@Param("id") Long id);
}
