package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.relation.RolePermission;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/4/14 10:14
 * @Description
 */
@Repository
public interface RolePermissionDao extends BaseMapper<RolePermission> {
}
