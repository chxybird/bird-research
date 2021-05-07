package com.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/5/6 14:09
 * @Description
 */
@Data
@TableName("t_coupon")
public class Coupon {
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private String name;
    private Integer stock;
}
