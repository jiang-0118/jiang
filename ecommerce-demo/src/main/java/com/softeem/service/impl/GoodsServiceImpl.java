package com.softeem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.softeem.constant.KeyConstant;
import com.softeem.entity.TEcommerceGoods;
import com.softeem.mapper.TEcommerceGoodsMapper;
import com.softeem.service.IGoodsService;
import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.SimpleGoodsInfo;
import com.softeem.vo.TableId;
import lombok.extern.log4j.Log4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Resource
    private TEcommerceGoodsMapper goodsMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public ResultInfo findGoodsByIds(TableId ids) {

        //校验参数
        AssertUtil.isNotNull(ids,"查询参数不可为空");
        //将参数提取
        List<Long> goodsId = ids.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        System.out.println("goodsId = " + goodsId);
        //存储从redis中获取的商品
        List<SimpleGoodsInfo> goodsInfoFromCache=new ArrayList<>();
        //从缓存中获取商品
        goodsId.forEach(id->{
            Map goodsInfo = redisTemplate.opsForHash().entries(KeyConstant.GOODS_CACHE_KEY.getKey().concat(id.toString()));
            if (!goodsInfo.isEmpty()){
            SimpleGoodsInfo simpleGoodsInfo = BeanUtil.mapToBean(goodsInfo, SimpleGoodsInfo.class,false);
            goodsInfoFromCache.add(simpleGoodsInfo);
                System.out.println("从缓存中查找商品：" + simpleGoodsInfo);
            }
        });
        List<Long> idsFromCache = goodsInfoFromCache.stream().map(SimpleGoodsInfo::getId).collect(Collectors.toList());
        //判断商品是否取完 取完返回 未取完继续
        if (goodsId.size()==idsFromCache.size()){
            return ResultInfoFactory.buildSuccess(goodsInfoFromCache);
        }else {
            ArrayList<Long> subtractIds = new ArrayList<>(CollectionUtil.subtract(goodsId, idsFromCache));
          List<TEcommerceGoods> TEcommerceGoodsFromDB= goodsMapper.findGoodsByIds(subtractIds);
            List<SimpleGoodsInfo> goodsInfoFromDB = TEcommerceGoodsFromDB.stream().map(TEcommerceGoods::toSimple).collect(Collectors.toList());
            //从DB中查询剩下的商品 并写入缓存
            goodsInfoFromDB.forEach(goodsInfo->{
                String key=KeyConstant.GOODS_CACHE_KEY.getKey().concat(goodsInfo.getId().toString());
                redisTemplate.opsForHash().putAll(key,BeanUtil.beanToMap(goodsInfo));
                System.out.println("将商品信息写入缓存" + goodsInfo);
            });
            //整合返回
            Collection<SimpleGoodsInfo> union = CollectionUtil.union(goodsInfoFromCache, goodsInfoFromDB);
            return ResultInfoFactory.buildSuccess(union);
        }
    }
}
