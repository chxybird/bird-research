package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.LoginUser;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/4/14 10:09
 * @Description
 */
@Repository
public interface UserDao extends BaseMapper<LoginUser> {
}
