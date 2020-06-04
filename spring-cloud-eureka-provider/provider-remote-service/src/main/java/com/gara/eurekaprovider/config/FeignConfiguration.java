package com.gara.eurekaprovider.config;

import feign.Contract;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;


/**
 * @description: Feign配置
 * @author: GaraYing
 * @createTime: 2019-11-14 13:45
 * @Version: 1.0
 **/

@Configuration
public class FeignConfiguration {

    //这个是日志的
    @Bean
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
}
