package com.softeem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Title: UserService
 * @Author Jiang
 * @Package com.softeem.service
 * @Date 2024/7/12 21:28
 * @description:
 */
@Component
public class UserService {
    @Autowired
    private DataSource dataSource;
    public void getConn() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
