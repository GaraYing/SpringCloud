package com.gara.eurekaprovider.service.impl;

import com.gara.eurekaprovider.service.Dc2Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Dc2ServiceFallBackServiceImpl implements Dc2Service {

    private Throwable cause;

    public Dc2ServiceFallBackServiceImpl(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String consumer() {
        log.error("failed with Feign Remote:异常信息={}",this.cause.getMessage());
        return "failed with Feign Remote";
    }
}
