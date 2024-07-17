package com.softeem.service;

import com.softeem.util.ResultInfo;

/**
 * @Title: IFollowService
 * @Author Jiang
 * @Package com.softeem.service
 * @Date 2024/7/17 11:28
 * @description:
 */
public interface IFollowService {
    /**
     * 关注/取关
     * @param followDinerId 要关注的人
     * @param status 具体操作 1关注 0取关
     * @return
     */
    ResultInfo follow(Integer followDinerId,Integer status);

    /**
     * 查询相同关注
     * @param followingDinerId 目标id
     * @return
     */
    ResultInfo findCommonsFriends(Integer followingDinerId);
}
