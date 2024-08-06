package com.softeem.mapper;


import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-08-06
 */
public interface TEcommerceRoleMapper {
    @Select("select `right` from t_ecommerce_role where roleid=(select roleid from user_role where userid=#{userId})")
    String findByUserId(int userId);
}
