package com.bird.dao;

import com.bird.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/5/26 14:37
 * @Description
 */
@Repository
public interface StudentMongoDao extends MongoRepository<Student,Long> {

}
