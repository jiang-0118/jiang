package com.softeem.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
@Getter
public enum RedisKeyConstant {
    // com.softeem.mapper bookMapper userMapper
    // com:softeem:mapper:bookMapper
    SIMPLE_GOODS_KEY("simpleGoods:", "简单商品信息Key"),
    FOLLOWER_DINER_KEY("follower_diner_key:", "粉丝列表"),
    FOLLOWING_DINER_KEY("following_diner_key:", "关注人列表"),
    FEEDS_KEY("feeds_key","动态列表");

    private String key;
    private String description;


}
