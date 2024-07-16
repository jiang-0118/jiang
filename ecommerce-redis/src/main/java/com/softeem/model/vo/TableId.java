package com.softeem.model.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * Id vo类
 * vo：值对象 ，避免将实体入参
 *   1. 实体中属性过多，很多属性不需要作为参数
 *   2. 实体中往往会有一些敏感的属性，不应该暴露实体的结构
 *   客户端-----vo-----接口
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableId {

    private List<Id> ids;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Id{
        private Long id;
    }
}
