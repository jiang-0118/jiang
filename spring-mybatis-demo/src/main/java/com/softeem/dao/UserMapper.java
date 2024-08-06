package com.softeem.dao;

import com.softeem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Title: UserDao
 * @Author Jiang
 * @Package com.softeem.dao
 * @Date 2024/7/13 20:34
 * @description:
 */
/*@Repository*/
@Mapper
public interface UserMapper {
   /* @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUsername(String username){
        return jdbcTemplate.queryForObject("select * from user where username=?",
                new BeanPropertyRowMapper<>(User.class),username);
    }*/
    @Select("select * from user where username = #{username}")
    //自动处理结果集-->对象
    public User findByUsername(String username);

}
