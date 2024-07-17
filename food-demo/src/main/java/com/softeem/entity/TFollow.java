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
public class TFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer dinerId;

    private Integer followDinerId;

    private int isValid;

    private Date createDate;

    private Date updateDate;


}
