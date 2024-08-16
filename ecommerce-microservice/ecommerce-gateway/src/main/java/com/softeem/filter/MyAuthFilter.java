package com.softeem.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: JY
 * @CreateTime: 2024-08-13
 * @Description: 局部过滤器
 * @Version: 1.0
 */
@Component
public class MyAuthFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //通过请求头获取校验信息
        String comp = request.getHeaders().getFirst("comp");
        //进行校验
        if (comp.equals("softeem")){
            //成功返回
           return chain.filter(exchange);
        }
        //非成功返回
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
