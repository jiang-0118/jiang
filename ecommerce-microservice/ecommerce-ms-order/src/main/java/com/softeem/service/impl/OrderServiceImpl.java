package com.softeem.service.impl;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.softeem.entity.TEcommerceOrder;
import com.softeem.mapper.TEcommerceOrderMapper;
import com.softeem.service.IOrderService;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.*;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Author: JY
 * @CreateTime: 2024-08-12
 * @Description:
 * @Version: 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private TEcommerceOrderMapper orderMapper;
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public ResultInfo createOrder(OrderInfo orderInfo, String token) {
        //校验订单参数
        AssertUtil.isNotNull(orderInfo,"订单不可为空");
        //获取当前用户信息
        CurrentUserInfo currentUserInfo = JwtUtil.parseToken(token);
        log.info("当前用户：{}",currentUserInfo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token",token);
        //扣库存
        List<DeductGoodsInventory> deduct = orderInfo.getOrderItems().stream().map(OrderInfo.OrderItem::toDeductGoodsInventory).collect(Collectors.toList());
        //整合为Map便于后续算钱操作
        Map<Long, Long> deductMap = deduct.stream().collect(Collectors.toMap(DeductGoodsInventory::getGoodsId, DeductGoodsInventory::getCount));
        String deductInventoryUrl="http://ms-goods/goods/deductGoods";
        HttpEntity<List<DeductGoodsInventory>> deductEntity = new HttpEntity<>(deduct, headers);
        //发送扣库存请求
        ResponseEntity<ResultInfo> deductResponse = restTemplate.postForEntity(deductInventoryUrl, deductEntity, ResultInfo.class);
        AssertUtil.isTrue(deductResponse.getStatusCode().value()!=HttpStatus.HTTP_OK,"扣减库存失败");
        log.info("成功执行扣减库存");
        //查询商品价格
        String findGoodsUrl="http://ms-goods/goods/list";
        List<TableId.Id> ids = deduct.stream().map(DeductGoodsInventory::getGoodsId).collect(Collectors.toList()).stream().map(TableId.Id::new).collect(Collectors.toList());
        TableId tableId = new TableId(ids);
        HttpEntity<TableId> goodsEntity = new HttpEntity<>(tableId, headers);
        //发送查询商品详情请求
        ResponseEntity<ResultInfo> goodsResponse = restTemplate.postForEntity(findGoodsUrl, goodsEntity, ResultInfo.class);
        AssertUtil.isTrue(goodsResponse.getStatusCode().value()!=HttpStatus.HTTP_OK,"商品查询失败");
        Object data = goodsResponse.getBody().getData();
        String json = JSON.toJSONString(data);
        List<SimpleGoodsInfo> goodsInfoList = JSON.parseArray(json, SimpleGoodsInfo.class);
        // goods/deductGoodsInventory   List<DeductGoodsInventory>
        goodsInfoList = JSON.parseArray(
                JSON.toJSONString(goodsInfoList), SimpleGoodsInfo.class
        );
        log.info("成功执行查询商品：{}",goodsInfoList);
        //算钱
        AtomicReference<Long> sum= new AtomicReference<>(0L);

        goodsInfoList.forEach(goods -> {
            sum.updateAndGet(v -> v + deductMap.get(goods.getId()) * goods.getPrice());
        });
        //扣钱
        BalanceInfo balanceInfo = new BalanceInfo();
        balanceInfo.setUserId(currentUserInfo.getId());
        balanceInfo.setBalance(sum.get());
        log.info("此次订单费用：{}",sum.get());
        //发送扣减余额请求
        String balanceUrl="http://ms-balance/balance/deductBalance";
        HttpEntity<BalanceInfo> balanceEntity = new HttpEntity<>(balanceInfo, headers);
        ResponseEntity<ResultInfo> balanceResponse = restTemplate.postForEntity(balanceUrl, balanceEntity, ResultInfo.class);
        AssertUtil.isTrue(balanceResponse.getStatusCode().value()!=HttpStatus.HTTP_OK,"扣减余额失败");
        log.info("成功执行扣减余额");
        System.out.println(1/0);
        //订单持久化
        TEcommerceOrder tEcommerceOrder = new TEcommerceOrder();
        tEcommerceOrder.setUserId(currentUserInfo.getId());
        tEcommerceOrder.setOrderDetail(JSON.toJSONString(deduct));
        int row=orderMapper.insert(tEcommerceOrder);
        AssertUtil.isTrue(row<1,"订单生成失败");
        return ResultInfoFactory.buildSuccess("订单生成成功");
    }
}
