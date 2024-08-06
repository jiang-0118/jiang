package com.softeem.entity;

import lombok.*;

/**
 * @Title: User
 * @Author Jiang
 * @Package com.softeem.entity
 * @Date 2024/7/13 20:34
 * @description: 用户类
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private int id;
    private String username;
    private String password;
}
