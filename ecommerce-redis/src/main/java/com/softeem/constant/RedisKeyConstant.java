package com.softeem.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RedisKeyConstant {
    // com.softeem.mapper bookMapper userMapper
    // com:softeem:mapper:bookMapper
    SIMPLE_GOODS_KEY("simpleGoods:","简单商品信息Key");
    private String key;
    private String description;



}
