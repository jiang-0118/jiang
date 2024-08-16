package com.softeem.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.softeem.constant.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: JY
 * @CreateTime: 2024-08-13
 * @Description: 路由配置信息类
 * @Version: 1.0
 */
@Component
@Slf4j
public class DynamicRouteDefinitionConfig {
    // 不可以注入，需要自己手动初始化
    // ConfigService： 用于访问Nacos中保存的配置信息的对象
    private ConfigService configService;
    @Resource
    private DynamicRouteDefinitionService dynamicRouteDefinitionService;

    //当ioc实例化的时候自动创建
    @PostConstruct
    public void init() throws NacosException {
        Properties properties = new Properties();
        //配置连接信息
        properties.setProperty("serverAddr", SystemConstant.NACOS_SERVER_ADDR);
        properties.setProperty("namespace",SystemConstant.NAMESPACE);
        configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig(
                SystemConstant.CONFIG_DATA_ID,
                SystemConstant.CONFIG_GROUP,
                SystemConstant.CONFIG_DEFAULT_TIMEOUT);
        log.info("读取到路由配置：{}",config);
        // 当前读取到的路由定义
        List<RouteDefinition> routeDefinitions = JSON.parseArray(config, RouteDefinition.class);
        routeDefinitions.forEach(r->dynamicRouteDefinitionService.addRouteDefinition(r));
        // 更新到网关中
        // 网关：Reactor架构，所有的路由数据都是需要主动取消费才能生效
        configService.addListener(SystemConstant.CONFIG_DATA_ID, SystemConstant.CONFIG_GROUP,
                new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return null;
                    }
                    @Override
                    public void receiveConfigInfo(String s) {
                        log.info("监听到配置变化:{}",s);
                        List<RouteDefinition> routeDefinitions1 = JSON.parseArray(s, RouteDefinition.class);
                        dynamicRouteDefinitionService.updateList(routeDefinitions1);
                        log.info("路由更新完毕...");
                    }
                });

    }

    }
