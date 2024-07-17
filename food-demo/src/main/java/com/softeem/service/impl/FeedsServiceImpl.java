package com.softeem.service.impl;

import com.softeem.constant.RedisKeyConstant;
import com.softeem.entity.TFeeds;
import com.softeem.mapper.TFeedsMapper;
import com.softeem.mapper.TFollowMapper;
import com.softeem.service.IFeedsService;
import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.CurrentUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Title: FeedsServiceImpl
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/17 17:13
 * @description: 发布动态
 */
@Service
public class FeedsServiceImpl implements IFeedsService {
    @Autowired
    private TFeedsMapper tFeedsMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    //发布动态
    @Override
    public ResultInfo create(String content) {
        //todo
        //校验参数
        AssertUtil.isNotEmpty(content, "动态内容不可为空");
        //获取用户信息
        CurrentUserInfo userInfo = (CurrentUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TFeeds tFeeds = new TFeeds();
        tFeeds.setContent(content);
        tFeeds.setFkDinerId(userInfo.getId());
        //在DB生成这条动态
        int row = tFeedsMapper.addFeeds(tFeeds);
        //获取当前用户粉丝ID集
        Set fansIds = redisTemplate.opsForSet().members(RedisKeyConstant.FOLLOWER_DINER_KEY.getKey().concat(userInfo.getId().toString()));
        fansIds.add(String.valueOf(tFeeds.getId()));
        //在粉丝和自己的动态列表(zset用时间线排序)添加自己发布动态的id
        fansIds.forEach(fanId -> {
            String feedsKey = RedisKeyConstant.FEEDS_KEY.getKey().concat(userInfo.getId().toString());
            redisTemplate.opsForZSet().add(feedsKey, tFeeds.getId().toString(), ((double) System.currentTimeMillis()));
        });
        return ResultInfoFactory.buildSuccess("发布动态成功");
    }
    //同步动态
    @Override
    public ResultInfo addFollowingFeeds(Integer followingDinerId, Integer status) {
        //todo
        //校验参数
        AssertUtil.isNotNull(followingDinerId, "目标用户不能为空");
        AssertUtil.isNotNull(status, "未知操作");
        //获取用户信息
        CurrentUserInfo userInfo = (CurrentUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //从DB中查询目标发布的动态
        List<TFeeds> followingFeeds = tFeedsMapper.findFeedsById(followingDinerId);
        System.out.println("followingFeeds = " + followingFeeds);
        //用户动态列表key
        String key = RedisKeyConstant.FEEDS_KEY.getKey().concat(userInfo.getId().toString());
        //id集
        Object[] arrayIds = followingFeeds.stream().map(feeds -> feeds.getId().toString()).collect(Collectors.toList()).toArray();
        //在当前用户的动态列表中对目标发布动态进行操作
        if (status == 0) {
            redisTemplate.opsForZSet().remove(key, arrayIds);
        } else {
            // [（消息发布时间 消息ID）,（消息发布时间 消息ID）,（权重 value）]
            Set<DefaultTypedTuple> set = followingFeeds.stream().map(tFeeds -> new DefaultTypedTuple(
                    tFeeds.getId().toString(),
                    ((double) tFeeds.getUpdateDate().getTime())
            )).collect(Collectors.toSet());
            if (!set.isEmpty()) {
                redisTemplate.opsForZSet().add(key, set);
            }
        }
        return ResultInfoFactory.buildSuccess("推送动态成功");
    }
}
