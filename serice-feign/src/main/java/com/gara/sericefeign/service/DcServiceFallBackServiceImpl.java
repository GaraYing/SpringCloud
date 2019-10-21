package com.gara.sericefeign.service;

/**
 * @description: 服务熔断
 * @author: GaraYing
 * @createTime: 2019-10-21 14:45
 * @Version: 1.0
 **/
public class DcServiceFallBackServiceImpl implements DcService{
    @Override
    public String consumer() {
        return "Get Service Failed";
    }
}
