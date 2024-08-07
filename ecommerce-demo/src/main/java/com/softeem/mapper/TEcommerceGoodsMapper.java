package com.softeem.mapper;


import com.softeem.entity.TEcommerceGoods;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-08-07
 */
public interface TEcommerceGoodsMapper  {

    List<TEcommerceGoods> findGoodsByIds(ArrayList<Long> ids);
}
