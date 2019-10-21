package com.gara.sericefeign.service;

import feign.hystrix.FallbackFactory;

/**
 * @description: 服务熔断
 * @author: GaraYing
 * @createTime: 2019-10-21 14:45
 * @Version: 1.0
 **/
public class HystrixClientFallbackFactory implements FallbackFactory<Dc2Service> {
    @Override
    public Dc2Service create(Throwable throwable) {
        return () -> "feign + hystrix ,提供者服务挂了";
    }
}
