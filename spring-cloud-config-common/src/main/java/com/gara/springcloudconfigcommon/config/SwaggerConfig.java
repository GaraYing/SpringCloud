package com.gara.springcloudconfigcommon.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger配置
 * @author:  GaraYing
 * @createTime: 2020/5/19 19:39
 * @Version: 1.0
**/
@Configuration
@ConfigurationProperties(prefix = "swagger")
@ConditionalOnProperty(prefix = "swagger", value = "enable", havingValue = "true")
//@ConditionalOnExpression("${swagger.enable:false}")
//@ConditionalOnMissingBean
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Controller包路径
     * 通过application.yml中swagger.base-package配置
     */
    private String basePackage;

    @Bean
    public Docket createRestApi(){
        System.out.println("=================0001" + basePackage);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfoBuilder().build())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();

    }
    /**
     * 项目信息
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "swagger.api-info")
    public ApiInfoBuilder apiInfoBuilder() {
        return new ApiInfoBuilder();
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
