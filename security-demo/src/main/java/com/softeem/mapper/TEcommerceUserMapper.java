package com.softeem.mapper;


import com.softeem.entity.TEcommerceUser;

import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-08-06
 */

public interface TEcommerceUserMapper{
    @Select("select * from t_ecommerce_user where username = #{username}")
    TEcommerceUser findByUsername(String username);

}
