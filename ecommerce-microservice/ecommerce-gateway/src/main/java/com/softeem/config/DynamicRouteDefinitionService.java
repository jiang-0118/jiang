package com.softeem.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: JY
 * @CreateTime: 2024-08-13
 * @Description: 注册中心配置信息服务
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class DynamicRouteDefinitionService implements ApplicationEventPublisherAware {
    // 发布路由定义的对象
    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;
    // 获取路由信息
    @Resource
    private RouteDefinitionLocator routeDefinitionLocator;

    // Spring的事件推送对象，Spring针对各个组件都有准备对应的事件，例如网关，有刷新事件
    @Resource
    private ApplicationEventPublisher eventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(eventPublisher);
    }
    /**
     * 添加路由
     * @param routeDefinition
     * @return
     */
    public String addRouteDefinition(RouteDefinition routeDefinition){
        log.info("开始添加路由定义:{}", JSON.toJSONString(routeDefinition));
        // 发布给gateway网关
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe(); //发布一个路由队形并马上订阅它
        // 发布一个刷新路由的事件，Gateway会自动监听该事件，并执行监听方法，在方法中会更新路由定义
        this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return "SUCCESS";
    }


    public String deleteById(String id){
        log.info("删除路由定义");
        this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return "SUCCESS";
    }


    public String updateList(List<RouteDefinition> routeDefinitions){
        log.info("获取当前所有路由");
        List<RouteDefinition> definitions = routeDefinitionLocator.getRouteDefinitions()
                .buffer().blockFirst();
        definitions.forEach(d->deleteById(d.getId()));
        log.info("删除原有路由定义");
        log.info("准备添加新的路由定义");
        routeDefinitions.forEach(r->addRouteDefinition(r));
        return "SUCCESS";

    }
}
