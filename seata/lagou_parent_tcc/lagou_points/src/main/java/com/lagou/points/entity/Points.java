package com.lagou.points.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 积分实体类
 */
@Data
@TableName("t_points")
public class Points {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;//积分ID
    @TableField
    private String username;//用户名
    @TableField
    private Integer points;//增加的积分
    @TableField
    private Integer frozenPoints;
}
