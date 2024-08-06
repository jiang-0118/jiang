package com.softeem.model.dto;


/**
 * DTO： 接口-- 数据传输对象 -- 客户端
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>简单的商品信息: 封面</h1>
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleGoodsInfo {

    private Long id;

    private String goodsName;

    private String goodsPic;

    private Integer price;

    public SimpleGoodsInfo(Long id) {
        this.id = id;
    }

    // 延时双删策略
}
