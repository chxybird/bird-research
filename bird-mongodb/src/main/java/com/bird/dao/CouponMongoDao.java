package com.bird.dao;

import com.bird.entity.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author lipu
 * @Date 2021/5/28 9:10
 * @Description
 */
public interface CouponMongoDao extends MongoRepository<Coupon,Long> {
}
