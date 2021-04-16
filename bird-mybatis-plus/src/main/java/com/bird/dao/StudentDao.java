package com.bird.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bird.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/04/06 14:29
 * @Description
 */
@Repository
public interface StudentDao extends BaseMapper<Student> {

    Student findById(@Param("id") Long id);
}
