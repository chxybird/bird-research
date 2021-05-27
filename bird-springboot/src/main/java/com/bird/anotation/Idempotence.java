package com.bird.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author lipu
 * @Date 2021/5/27 10:53
 * @Description 幂等性校验注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @ interface Idempotence {
    /** 是否启用幂等性校验 */
    boolean isActive() default true;
}
