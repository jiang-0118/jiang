package com.softeem.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDiners implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    /**
     * 昵称
     */
    private String nickname;

    private String phone;

    private String email;

    private String password;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 角色
     */
    private String roles;

    private Boolean isValid;

    private Date createDate;

    private Date updateDate;


}
