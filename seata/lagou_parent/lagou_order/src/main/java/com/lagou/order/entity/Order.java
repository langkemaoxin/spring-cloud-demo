package com.lagou.order.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单实体类
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    @TableId
    private Long id;//订单id
    @TableField
    private Integer goodsId;// 商品ID
    @TableField
    private Integer num;//商品数量
    @TableField
    private Double money;//商品总金额
    @TableField
    private java.util.Date createTime;//订单创建时间
    @TableField
    private String username;//用户名称
}

