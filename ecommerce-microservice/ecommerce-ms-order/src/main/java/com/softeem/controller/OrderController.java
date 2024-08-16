package com.softeem.controller;

import com.softeem.service.IOrderService;
import com.softeem.util.ResultInfo;
import com.softeem.vo.OrderInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @Author: JY
 * @CreateTime: 2024-08-12
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private IOrderService iOrderService;
    @Resource
    private HttpServletRequest request;
    @RequestMapping("/createOrder")
    public ResultInfo createOrder(@RequestBody OrderInfo orderInfo){
        return iOrderService.createOrder(orderInfo,request.getHeader("token"));
    }
}
