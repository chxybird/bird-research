package com.bird.design.flyweight;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author lipu
 * @Date 2021/6/17 10:36
 * @Description 内部状态 共享的信息
 */
@Data
@EqualsAndHashCode
public class InnerStatus {
    //内部状态
    private String url;
    private String username;
    private String password;
    private String driver;
}
