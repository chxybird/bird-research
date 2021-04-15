package com.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author lipu
 * @Date 2021/4/14 10:07
 * @Description
 */
@Data
@TableName("t_permission")
public class Permission implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String uri;
}
