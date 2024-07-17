package com.softeem.mapper;


import com.softeem.entity.TDiners;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-17
 */
public interface TDinersMapper {
    @Select("select * from t_diners where username=#{username}")
     TDiners findByUsername(String username);

}
