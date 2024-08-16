package com.softeem.filter;

import com.softeem.expcetion.ParameterException;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.vo.CurrentUserInfo;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-08
 * @Description: 全局过滤器
 * @Version: 1.0
 */
@Component
public class GlobalAuthFilter implements GlobalFilter {

    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //Todo 过滤器常见操作
        //白名单放行
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        if(request.getPath().toString().contains("/login")){
            return chain.filter(exchange);
        }
        //校验凭证
        String token = request.getHeaders().getFirst("token");




        //远程访问checkToken
        /*String url="http://localhost:9000/authority/checkToken";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("token",token);
        HttpEntity<Object> entity = new HttpEntity<>(null,header);
        ResponseEntity<ResultInfo> responseEntity = restTemplate.postForEntity(url, entity, ResultInfo.class);
        if (responseEntity.getStatusCode()==HttpStatus.OK){
            return chain.filter(exchange);
        }*/

        try {
            CurrentUserInfo currentUserInfo = JwtUtil.parseToken(token);
            //处理上下文
            if (currentUserInfo !=null){
                return chain.filter(exchange);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ParameterException(ex.getMessage());
        }


        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //设置处理完毕
        return response.setComplete();
    }
}
