package com.softeem.mapper;


import com.softeem.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2024-07-14
 */
public interface UserMapper{
    /**
     * 带参数查询
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 多参数查询
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(
            @Param("username") String username,
            @Param("password") String password);
    //    /**
//     * 返回集合
//     * @return
//     */
    List<User> findUsers();
//
//
    /**
     * 添加
     * @param user
     * @return
     */
    int addUser(User user);
//
    /**
     * 修改
     * @param user
     * @return
     */
    int updateUser(User user);
//
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteUser(Integer id);

}
