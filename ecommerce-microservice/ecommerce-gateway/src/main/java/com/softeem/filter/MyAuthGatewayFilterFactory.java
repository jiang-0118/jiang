package com.softeem.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: JY
 * @CreateTime: 2024-08-13
 * @Description:
 * @Version: 1.0
 */
@Component
public class MyAuthGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return new MyAuthFilter();
    }
}
