package com.softeem.service;

import com.softeem.util.ResultInfo;
import com.softeem.vo.DeductGoodsInventory;
import com.softeem.vo.TableId;

import java.util.List;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
public interface IGoodsService {
    /**
     * 批量查询商品
     * @param ids
     * @return
     */
    ResultInfo findGoodsByIds(TableId ids,String token);


    /**
     * 扣减商品库存
     * @param deductGoodsInventories
     * @return
     */
    ResultInfo deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories,String token);
}
