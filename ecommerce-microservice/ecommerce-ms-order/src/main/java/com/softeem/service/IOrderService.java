package com.softeem.service;

import com.softeem.util.ResultInfo;
import com.softeem.vo.OrderInfo;

/**
 * @Author: JY
 * @CreateTime: 2024-08-12
 * @Description:
 * @Version: 1.0
 */
public interface IOrderService {
    /**
     * 下单操作
     * @param orderInfo
     * @param token
     * @return
     */
    ResultInfo createOrder(OrderInfo orderInfo, String token);
}
