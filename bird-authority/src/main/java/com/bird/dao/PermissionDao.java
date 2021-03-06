package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/4/14 10:10
 * @Description
 */
@Repository
public interface PermissionDao extends BaseMapper<Permission> {

    List<Permission> findByRoleId(Long roleId);



}
