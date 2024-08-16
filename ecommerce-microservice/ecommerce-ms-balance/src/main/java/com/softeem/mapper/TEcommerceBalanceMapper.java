package com.softeem.mapper;

import com.softeem.entity.TEcommerceBalance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Author: JY
 * @CreateTime: 2024-08-12
 * @Description:
 * @Version: 1.0
 */
public interface TEcommerceBalanceMapper {
    @Select("select * from t_ecommerce_balance where user_id = #{userId}")
    TEcommerceBalance selectById(Long userId);

    @Insert("insert into values(null,#{userId},#{balance},now(),now())")
    void insert(TEcommerceBalance createBalance);
    @Update("UPDATE t_ecommerce_balance set balance=#{balance} where user_Id=#{userId}")
    void update(@Param("balance") Long balance,@Param("userId") Long userId);
}
