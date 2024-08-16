package com.softeem.mapper;


import com.softeem.entity.TEcommerceOrder;
import org.apache.ibatis.annotations.Insert;

/**
 * <p>
 * 用户订单表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-08-12
 */
public interface TEcommerceOrderMapper {
    @Insert("insert into t_ecommerce_order values(null,#{userId},'0',#{orderDetail},now(),now())")
    int insert(TEcommerceOrder tEcommerceOrder);
}
