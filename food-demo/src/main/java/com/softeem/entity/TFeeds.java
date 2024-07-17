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
public class TFeeds implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 内容
     */
    private String content;

    private Integer fkDinerId;

    /**
     * 点赞数量
     */
    private Integer praiseAmount;

    /**
     * 评论数量
     */
    private Integer commentAmount;

    private Integer fkRestaurantId;

    private Date createDate;

    private Date updateDate;

    private Boolean isValid;


}
