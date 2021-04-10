package com.lagou.storage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_storage")
public class Storage {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;//库存ID
    @TableField
    private String goodsId;//商品ID
    @TableField
    private Integer storage;//库存量

    @TableField
    private Integer frozenStorage;
}
