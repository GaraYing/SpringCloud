package com.gara.eurekaprovider.config;

import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.UUID;


/**
 * @description: Feign配置
 * @author: GaraYing
 * @createTime: 2019-11-14 13:45
 * @Version: 1.0
 **/

@Configuration
@Slf4j
public class FeignConfiguration {
    // 打印日志
    @Bean
    @ConditionalOnProperty(name = "feign.log.enabled", havingValue = "true", matchIfMissing = true)
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    // 启用Fegin自定义注解 如@RequestLine @Param
    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    // new一个form编码器，实现支持form表单提交

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder feignEncoder() {
//        return new FeignFormEncoder(new SpringEncoder(messageConverters));
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    // 请求连接和读取数据的超时时间(ms)
    @Bean
    public Request.Options timeoutConfiguration(){
        return new Request.Options(10 * 1000, 30 * 1000);
    }

    @Bean
    public RequestInterceptor requestLoggingInterceptor() {
        return template -> {
            log.info("Adding header [testHeader / testHeaderValue] to request");
            template.header("testHeader", "testHeaderValue");
            template.header("requestId", String.valueOf(UUID.randomUUID()));
        };
    }


    // 请求认证
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

    // 请求重试机制
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 8000, 3);
    }
}
