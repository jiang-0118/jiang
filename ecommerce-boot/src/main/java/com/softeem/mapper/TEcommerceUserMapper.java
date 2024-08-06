package com.softeem.mapper;


import com.softeem.model.entity.TEcommerceUser;
import com.softeem.model.vo.UsernameAndPassword;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-15
 */
@Mapper
public interface TEcommerceUserMapper {
    TEcommerceUser findUserByUsername(String username);

}
