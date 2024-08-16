package com.softeem.controller;

import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.UsernameAndPassword;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private RestTemplate restTemplate;
    //发现客户端
    /*@Resource
    private DiscoveryClient discoveryClient;*/
    //负载均衡
   /* @Resource
    private LoadBalancerClient loadBalancerClient;*/
    @RequestMapping("/login")
    ResultInfo login(@RequestBody UsernameAndPassword usernameAndPassword){
        AssertUtil.isNotNull(usernameAndPassword,"用户信息不能为空");
        AssertUtil.isNotEmpty(usernameAndPassword.getUsername(),"用户账号不能为空");
        AssertUtil.isNotEmpty(usernameAndPassword.getPassword(),"用户密码不能为空");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(usernameAndPassword,header);

        /*DiscoveryClient client = discoveryClient;
        //正常返回的是集合，代表该服务的集群
        ServiceInstance instance = client.getInstances("authority-center").get(0);*/
        //自动在集群中选取适合的服务端
        /*ServiceInstance instance = loadBalancerClient.choose("authority-center");
        String host = instance.getHost();
        Integer port = instance.getPort();*/
        //discoveryClient的拼接url
        //String url="http://localhost:9000/authority/token";
        //loadBalancerClient的拼接url
//        String url="http://".concat(host+":").concat(port.toString()).concat("/authority/token");

        String url="http://authority-center/authority/token";
        System.out.println("url = " + url);
        ResponseEntity<ResultInfo> response = restTemplate.postForEntity(url, entity,ResultInfo.class);
        if (response.getStatusCode()== HttpStatus.OK){
            ResultInfo resultInfo = response.getBody();
            return ResultInfoFactory.buildSuccess(resultInfo);
        }
        return ResultInfoFactory.buildError("登录失败");
    }
}
