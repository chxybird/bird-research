package com.bird.design.flyweight;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/6/17 10:36
 * @Description 外部状态 非共享的信息
 */
@Data
@AllArgsConstructor
public class OuterStatus {
    private String name;
    private String uuid;
}
