package com.softeem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.softeem.constant.RedisKeyConstant;
import com.softeem.mapper.TEcommerceGoodsMapper;
import com.softeem.model.dto.SimpleGoodsInfo;
import com.softeem.model.entity.TEcommerceGoods;
import com.softeem.model.vo.TableId;
import com.softeem.service.IGoodsService;
import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Title: GoodsServiceImpl
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/16 13:48
 * @description:
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TEcommerceGoodsMapper goodsMapper;

    @Override
    public ResultInfo findSimpleGoods(TableId tableId) {
        //todo
        //校验参数
        AssertUtil.isTrue(tableId.getIds().isEmpty(), "参数不可为空");
        //获取数据
        List<Long> ids = tableId.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        //从缓存中查询数据
        //根据key在redis中查数据
        List<SimpleGoodsInfo> simpleGoodsInfosFromCache = new ArrayList<>();
        ids.forEach(id -> {
            String key = RedisKeyConstant.SIMPLE_GOODS_KEY.getKey().concat(id.toString());
            Map goodsInfo = redisTemplate.opsForHash().entries(key);
            if (!goodsInfo.isEmpty()) {
                simpleGoodsInfosFromCache.add(BeanUtil.mapToBean(goodsInfo, SimpleGoodsInfo.class, false));
            }
        });
        //判断是否查完
        if (ids.size() == simpleGoodsInfosFromCache.size()) {
            return ResultInfoFactory.buildSuccess("查询成功", simpleGoodsInfosFromCache);
        } else {
            //若未查完则取差集查DB
            List<Long> idsFromRedis = simpleGoodsInfosFromCache.stream().map(SimpleGoodsInfo::getId).collect(Collectors.toList());
            List<Long> subtractIds = (List<Long>) CollectionUtil.subtract(ids, idsFromRedis);
            List<SimpleGoodsInfo> simpleGoodsInfoFromDB = goodsMapper.findGoodsByIds(subtractIds).stream()
                    .map(TEcommerceGoods::toSimpleGoods).collect(Collectors.toList());
            //写入缓存
            simpleGoodsInfoFromDB.forEach(info -> {
                String key = RedisKeyConstant.SIMPLE_GOODS_KEY.getKey().concat(info.getId().toString());
                redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(info));
            });
            //结果返回
            List<SimpleGoodsInfo> union = (List<SimpleGoodsInfo>) CollectionUtil.union(simpleGoodsInfoFromDB, simpleGoodsInfosFromCache);
            return ResultInfoFactory.buildSuccess(union);
        }
    }
}
