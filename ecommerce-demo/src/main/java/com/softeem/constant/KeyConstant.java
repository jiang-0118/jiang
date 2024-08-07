package com.softeem.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KeyConstant {

    GOODS_CACHE_KEY("GOODS_CACHE_KEY:","简单商品信息缓存key");
    private String key;
    private String description;
}
