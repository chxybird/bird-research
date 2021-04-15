package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/4/14 10:10
 * @Description
 */
@Repository
public interface RoleDao extends BaseMapper<Role> {

    List<Role> findByUserId(Long userId);
}
