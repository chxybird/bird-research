package com.bird.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author lipu
 * @Date 2021/5/27 18:00
 * @Description
 */
@Data
@Document(collection = "coupon")
public class Coupon {
    @Id
    private Long id;
    private String couponName;
}
