package com.softeem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



/// 购买商品 下订单时的值对象
// 每一个OrderItem代表某个商品购买多件
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderInfo {
    private List<OrderItem> orderItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItem{
        private Long goodsId;
        private Long count;

        public DeductGoodsInventory toDeductGoodsInventory(){
            return new DeductGoodsInventory(
                    this.goodsId,
                    this.count
            );
        }

// orderItems: [{goodsId: 1,count:5},{goodsId: 12,count:5},{goodsId: 14,count:5}]
// 



    }
}
