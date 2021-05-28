package com.bird.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/5/27 17:59
 * @Description
 */
@Data
@Document(collection = "activity")
public class Activity {
    @Id
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    //内嵌文档
    private Organization organization;
    //内嵌关联文档
    @DBRef
    private List<Coupon> couponList;
}
