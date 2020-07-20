package com.gara.eurekaprovider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HealthIndicator  extends AbstractHealthIndicator {

    @Autowired
    private Environment environment;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        log.info("running health check for {}", environment.getProperty("local.server.port"));
        builder.up();
    }
}
