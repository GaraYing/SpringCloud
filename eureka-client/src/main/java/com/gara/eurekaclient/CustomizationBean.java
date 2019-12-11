package com.gara.eurekaclient;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: server自定义启动 port
 * @author:  Gara
 * @createTime: 2019/12/11 17:23
 * @Version: 1.0
**/

@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    /**
     *
     * {@link TomcatServletWebServerFactory } and {@link JettyServletWebServerFactory} and {@link UndertowServletWebServerFactory}
     *  are dedicated variants of {@link ConfigurableServletWebServerFactory} that have additional customization setter methods for Tomcat, Jetty and Undertow respectively.
     *
     * @param factory
     */

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(9999);
    }
}
