package com.bird.entity.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/4/14 10:11
 * @Description
 */
@Data
@TableName("t_user_role")
public class UserRole {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long roleId;

}
