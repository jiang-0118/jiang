package com.softeem.dao;

import com.softeem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // BeanPropertyRowMapper: 映射 bean 和 行的关系的对象
    public User findByUsernameAndPassword(String username,String password){
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE USERNAME =? AND PASSWORD= ?",
                new BeanPropertyRowMapper<>(User.class), username, password);
    }


    public int addUser(String username,String password){
        return jdbcTemplate.update("insert into user(username,password) values(?,?)",username,password);
    }

    public List<User> findUsers(){
        return jdbcTemplate.query("select * from user",new BeanPropertyRowMapper(User.class));
    }

}
