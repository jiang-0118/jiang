package com.softeem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleGoodsInfo {

    private Long id;
    private Integer price;
    private String goodsName;
    private Long inventory;

    public SimpleGoodsInfo(Long id, long inventory) {
        this.id=id;
        this.inventory=inventory;
    }
}
