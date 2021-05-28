package com.bird.aop;

import com.bird.anotation.Idempotence;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Author lipu
 * @Date 2021/5/27 10:56
 * @Description 幂等性校验切面
 */
@Aspect
@Slf4j
@Component
public class IdempotenceAop {

    private static final String KEY = "idempotence";
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Pointcut("@annotation(com.bird.anotation.Idempotence)")
    public void idempotencePointcut() {

    }

    /**
     * @Author lipu
     * @Date 2021/5/27 11:00
     * @Description 前置通知 接口幂等性校验逻辑
     */
    @Around("idempotencePointcut()")
    public Object checkIdempotence(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Idempotence idempotence = method.getAnnotation(Idempotence.class);
        if (idempotence.isActive()) {
            //执行幂等性校验逻辑 redisTemplate.hasKey(KEY)判断key是否存在也可以
            String value =(String) redisTemplate.opsForValue().get(KEY);

            //第一次提交
            if (value==null){
                //这里的KEY应该存每个用户的唯一标识,这里作为demo,就固定一下,思路不变
                redisTemplate.opsForValue().set(KEY,KEY,30, TimeUnit.SECONDS);
                return joinPoint.proceed();
            }else {
                //多次提交
                return "不能重复提交!!!";
            }
        } else {
            //直接放行执行业务逻辑
            return joinPoint.proceed();
        }
    }


}
