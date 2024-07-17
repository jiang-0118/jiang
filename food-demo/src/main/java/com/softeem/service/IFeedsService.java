package com.softeem.service;

import com.softeem.util.ResultInfo;

/**
 * @Title: IFeedsService
 * @Author Jiang
 * @Package com.softeem.service
 * @Date 2024/7/17 17:07
 * @description:
 */
public interface IFeedsService {
    /**
     * 发布动态 当前用户
     * @return
     */
    ResultInfo create(String content);


    /**
     * 在关注取关中维护动态列表
     * @param followingDinerId 目标用户ID
     * @param status 操作是关注还是取关
     * @return
     */
    ResultInfo addFollowingFeeds(Integer followingDinerId,Integer status);
}
