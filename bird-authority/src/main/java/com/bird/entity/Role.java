package com.bird.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/4/14 9:55
 * @Description
 */
@TableName("t_role")
@Data
public class Role implements Serializable , GrantedAuthority {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String code;
    private String description;
    @TableField(exist = false)
    private LoginUser loginUser;
    @TableField(exist = false)
    List<Permission> permissionList;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return code;
    }
}
