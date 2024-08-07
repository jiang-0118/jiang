package com.softeem.entity;


import java.io.Serializable;
import java.util.Date;

import com.softeem.vo.SimpleGoodsInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author author
 * @since 2024-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class TEcommerceGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */

    private Long id;

    /**
     * 商品类别
     */
    private String goodsCategory;

    /**
     * 品牌分类
     */
    private String brandCategory;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsPic;

    /**
     * 商品描述信息
     */
    private String goodsDescription;

    /**
     * 商品状态
     */
    private Integer goodsStatus;

    /**
     * 商品价格
     */
    private Integer price;

    /**
     * 总供应量
     */
    private Long supply;

    /**
     * 库存
     */
    private Long inventory;

    /**
     * 商品属性
     */
    private String goodsProperty;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public SimpleGoodsInfo toSimple(){
      return new SimpleGoodsInfo(this.id,
              this.price,
              this.goodsName,
              this.inventory)  ;
    }

}
