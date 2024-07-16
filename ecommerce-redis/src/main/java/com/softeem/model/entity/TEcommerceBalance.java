package com.softeem.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户账户余额表
 * </p>
 *
 * @author author
 * @since 2024-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class TEcommerceBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */

    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 账户余额
     */
    private Long balance;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 更新时间
     */

    private LocalDateTime updateTime;


}
