package com.bird.design.chain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author lipu
 * @Date 2021/6/17 17:10
 * @Description 请求 模拟一个请假条
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    /* 请假条编号 */
    private String uuid;
    /* 请假时间 单位天数 */
    private Integer day;
    /* 发起时间 */
    private Date date;
    /* 处理结果 */
    private String result;
}
