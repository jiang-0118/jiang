package com.softeem.entity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户订单表
 * </p>
 *
 * @author author
 * @since 2024-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class TEcommerceOrder implements Serializable {

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
     * 用户地址记录 id
     */
    private Long addressId;

    /**
     * 订单详情(json 存储, goodsId, count)
     */
    private String orderDetail;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
