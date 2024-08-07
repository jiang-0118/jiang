package com.softeem.service;

import com.softeem.util.ResultInfo;
import com.softeem.vo.TableId;

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
    ResultInfo findGoodsByIds(TableId ids);
}
