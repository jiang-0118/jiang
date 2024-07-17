package com.softeem.service.impl;

import com.softeem.constant.RedisKeyConstant;
import com.softeem.entity.TFollow;
import com.softeem.mapper.TFollowMapper;
import com.softeem.service.IFollowService;
import com.softeem.util.AssertUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.CurrentUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Title: FollowServiceImpl
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/17 11:28
 * @description:关注业务
 */
@Service
public class FollowServiceImpl implements IFollowService {
    @Autowired
    private TFollowMapper tFollowMapper;
    @Autowired
    private FeedsServiceImpl feedsService;

    @Transactional
    @Override
    public ResultInfo follow(Integer followDinerId, Integer status) {
        //校验数据
        AssertUtil.isNotNull(followDinerId, "关注用户不可为空");
        AssertUtil.isNotNull(status, "未知操作");
        //获取当前用户信息
        CurrentUserInfo userInfo = (CurrentUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //DB查询与目标用户的关系(r)
        TFollow tFollow = tFollowMapper.findFollowByDinerId(userInfo.getId(), followDinerId);
        //重新关注业务r!=null,valid=0,status=1
        if (tFollow != null && tFollow.getIsValid() == 0 && status == 1) {
            int row = tFollowMapper.updateStatus(userInfo.getId(), followDinerId, status);

            AssertUtil.isTrue(row < 1, "关注失败");
            followInRedis(userInfo.getId(), followDinerId);
            feedsService.addFollowingFeeds(followDinerId, status);
            return ResultInfoFactory.buildSuccess("关注成功");

        }
        //新关注业务r=null,status=1
        if (tFollow == null && status == 1) {
            TFollow tFollow1 = new TFollow();
            tFollow1.setDinerId(userInfo.getId());
            tFollow1.setFollowDinerId(followDinerId);
            tFollow1.setIsValid(1);
            int row = tFollowMapper.addFollow(tFollow1);
            AssertUtil.isTrue(row < 1, "关注失败");
            followInRedis(userInfo.getId(), followDinerId);
            feedsService.addFollowingFeeds(followDinerId, status);
            return ResultInfoFactory.buildSuccess("关注成功");

        }

        //取关业务r!=null,isValid=1,status=0
        if (tFollow != null && tFollow.getIsValid() == 1 && status == 0) {
            int row = tFollowMapper.updateStatus(userInfo.getId(), followDinerId, status);
            AssertUtil.isTrue(row < 1, "取关失败");
            unfollowInRedis(userInfo.getId(), followDinerId);
            feedsService.addFollowingFeeds(followDinerId, status);
            return ResultInfoFactory.buildSuccess("取关成功");

        }
        //目标粉丝列表和用户关注列表维护
        return ResultInfoFactory.buildError("操作失败");
    }

    //找寻共同关注
    @Override
    public ResultInfo findCommonsFriends(Integer dinerId) {
        CurrentUserInfo userInfo = (CurrentUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userKey = RedisKeyConstant.FOLLOWING_DINER_KEY.getKey().concat(userInfo.getId().toString());
        String dinerKey = RedisKeyConstant.FOLLOWING_DINER_KEY.getKey().concat(dinerId.toString());
        Set intersect = redisTemplate.opsForSet().intersect(userKey, dinerKey);
        return ResultInfoFactory.buildSuccess(intersect);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    public void followInRedis(Integer userId, Integer dinerId) {
        //在用户关注里添加目标id
        String userKey = RedisKeyConstant.FOLLOWING_DINER_KEY.getKey().concat(userId.toString());
        redisTemplate.opsForSet().add(userKey, dinerId.toString());
        //在目标粉丝里添加用户id
        String followKey = RedisKeyConstant.FOLLOWER_DINER_KEY.getKey().concat(dinerId.toString());
        redisTemplate.opsForSet().add(followKey, userId.toString());
    }

    public void unfollowInRedis(Integer userId, Integer dinerId) {
        //在用户关注里添加目标id
        String userKey = RedisKeyConstant.FOLLOWING_DINER_KEY.getKey().concat(userId.toString());
        redisTemplate.opsForSet().remove(userKey, dinerId.toString());
        //在目标粉丝里添加用户id
        String followKey = RedisKeyConstant.FOLLOWER_DINER_KEY.getKey().concat(dinerId.toString());
        redisTemplate.opsForSet().remove(followKey, userId.toString());
    }
}
