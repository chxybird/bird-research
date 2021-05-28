package com.bird.dao;

import com.bird.entity.Activity;
import com.bird.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/5/27 18:03
 * @Description
 */
@Repository
public interface ActivityMongoDao extends MongoRepository<Activity,Long> {
}
