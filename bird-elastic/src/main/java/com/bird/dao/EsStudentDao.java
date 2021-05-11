package com.bird.dao;

import com.bird.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lipu
 * @Date 2021/5/11 9:08
 * @Description
 */
@Repository
public interface EsStudentDao extends ElasticsearchRepository<Student,String> {

}
