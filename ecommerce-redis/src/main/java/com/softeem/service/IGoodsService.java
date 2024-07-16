package com.softeem.service;

import com.softeem.model.vo.TableId;
import com.softeem.util.ResultInfo;

/**
 * @Title: IGoodsService
 * @Author Jiang
 * @Package com.softeem.service
 * @Date 2024/7/16 13:47
 * @description:
 */
public interface IGoodsService {
    /**
     * 根据ID查询商品信息
     * @param tableId id参数
     * @return
     */
    ResultInfo findSimpleGoods(TableId tableId);
}
