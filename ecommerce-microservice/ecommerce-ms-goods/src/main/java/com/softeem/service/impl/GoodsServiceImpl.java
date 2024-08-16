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
import com.softeem.vo.DeductGoodsInventory;
import com.softeem.vo.SimpleGoodsInfo;
import com.softeem.vo.TableId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@Service
@Slf4j
public class GoodsServiceImpl implements IGoodsService {
    @Resource
    private TEcommerceGoodsMapper goodsMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public ResultInfo findGoodsByIds(TableId ids,String token) {
        log.info("token:{}",token);

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
                log.info("从缓存中查找商品：{}" , simpleGoodsInfo);
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
                log.info("从缓存中查找商品：{}" , goodsInfo);
            });
            //整合返回
            Collection<SimpleGoodsInfo> union = CollectionUtil.union(goodsInfoFromCache, goodsInfoFromDB);

            return ResultInfoFactory.buildSuccess(union);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultInfo deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories,String token) {
        //校验参数
        AssertUtil.isNotNull(deductGoodsInventories,"商品信息不可为空");
        //将扣减库存信息整合为Map便于操作
        Map<Long, Long> deductMap = deductGoodsInventories.stream().collect(Collectors.toMap(DeductGoodsInventory::getGoodsId, DeductGoodsInventory::getCount));
        //从DB获取对应商品信息
        List<Long> ids = deductGoodsInventories.stream().map(DeductGoodsInventory::getGoodsId).collect(Collectors.toList());
        List<TEcommerceGoods> goodsInfos = goodsMapper.findGoodsByIds((ArrayList<Long>) ids);

        //从redis获取对应商品信息(不实际)
        /*List<TableId.Id> ids = deductGoodsInventories.stream().map(DeductGoodsInventory::getGoodsId).collect(Collectors.toList()).stream().map(TableId.Id::new).collect(Collectors.toList());
        TableId tableId = new TableId(ids);
        ArrayList<SimpleGoodsInfo> goodsInfos = (ArrayList<SimpleGoodsInfo>) findGoodsByIds(tableId, token).getData();*/
        //判断库存是否支持这次扣减
        ArrayList<SimpleGoodsInfo> resultList = new ArrayList<>();
        goodsInfos.forEach(goodsInfo->{
            Long deductInventory = deductMap.get(goodsInfo.getId());
            Long inventory = goodsInfo.getInventory();
            AssertUtil.isTrue(inventory<deductInventory,"库存不足");
            resultList.add(new SimpleGoodsInfo(goodsInfo.getId(),inventory-deductInventory));
        });
        //缓存删除这些数据
        resultList.forEach(result->{
            String redisKey=KeyConstant.GOODS_CACHE_KEY.getKey().concat(result.getId().toString());
            redisTemplate.delete(redisKey);
        });
        //DB更新数据
        int row=goodsMapper.updateBatch(resultList);
        if (row<1){
            return ResultInfoFactory.buildError("修改库存失败");
        }
        return ResultInfoFactory.buildSuccess("修改库存成功");
    }
}
