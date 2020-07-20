package com.gara.eurekaprovider.config;


import com.netflix.loadbalancer.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 配置Feign调用负载均衡策略
 * @author:  GaraYing
 * @createTime: 2020/7/20 16:44
 * @Version: 1.0
**/
@Slf4j
@RibbonClient(name="${spring.application.name}")
public class RibbonConfiguration{

    @Value("${spring.application.name}")
    private String serviceId;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Bean
    public IRule loadBalancingRule() {
        return new RoundRobinRule();
    }

//    @Bean
//    public IPing ribbonPing(IClientConfig config) {
//        return new PingUrl();
//    }

    // Health Check to ensure health instances by sending an HTTP request to http://my-server:port/actuator/health
    @Bean
    public IPing pingConfiguration(ServerList<Server> servers) {
        String pingPath = "/actuator/health";
        IPing ping = new PingUrl(false, pingPath);
        log.info("Configuring ping URI to [{}]", pingPath);
        return ping;
    }

    @Bean
    public ServerList<Server> serverList() {

        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        List<Server> serverList = new ArrayList<>(instances.size());
        instances.forEach(e->{
            Server server = new Server("http", e.getHost(), e.getPort());
            serverList.add(server);
        });
        return new ServerList<Server>() {
            @Override
            public List<Server> getUpdatedListOfServers() {
                log.info("Returning updated list of servers [{}]", serverList);
                return serverList;
            }
            @Override
            public List<Server> getInitialListOfServers() {
                return serverList;
            }
        };
    }
}
