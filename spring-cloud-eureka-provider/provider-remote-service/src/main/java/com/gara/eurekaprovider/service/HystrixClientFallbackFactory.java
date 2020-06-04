package com.gara.eurekaprovider.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 服务熔断
 * @author: GaraYing
 * @createTime: 2019-10-21 14:45
 * @Version: 1.0
 **/
@Component
public class HystrixClientFallbackFactory implements FallbackFactory<Dc2Service> {
    @Override
    public Dc2Service create(Throwable throwable) {
        return new Dc2ServiceFallBackServiceImpl(throwable);
//        return () -> "feign + hystrix ,提供者服务挂了";
    }
}
