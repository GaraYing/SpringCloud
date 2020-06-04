package com.gara.eurekaprovider.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
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
