package com.softeem.mapper;

import com.softeem.model.entity.TEcommerceGoods;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-16
 */
public interface TEcommerceGoodsMapper {
    List<TEcommerceGoods> findGoodsByIds(List<Long> ids);

}
