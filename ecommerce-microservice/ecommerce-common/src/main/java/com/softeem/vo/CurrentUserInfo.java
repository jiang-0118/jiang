package com.softeem.vo;

import lombok.Data;

@Data
public class CurrentUserInfo {
    private Long id;
    private String username;

    public CurrentUserInfo(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public CurrentUserInfo(){}
}
