package com.softeem.mapper;


import com.softeem.entity.TFollow;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-17
 */
public interface TFollowMapper{

    TFollow findFollowByDinerId(@Param("userId") Integer id,@Param("dinerId") Integer followDinerId);

    int updateStatus(@Param("userId")Integer id,@Param("dinerId") Integer followDinerId,@Param("isValid") Integer status);

    int addFollow(TFollow tFollow1);
}
