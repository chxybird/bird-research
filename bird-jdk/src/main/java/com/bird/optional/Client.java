package com.bird.optional;

import com.bird.stream.material.Student;

import java.util.Optional;

/**
 * @Author lipu
 * @Date 2021/1/23 17:29
 * @Description Optional
 */
public class Client {
    public static void main(String[] args) {
        //模拟数据为空
        Student student=null;
        //创建optional
        Optional<Student> optional = Optional.ofNullable(student);
        //存在做什么,不存在什么都不做
        optional.ifPresent((item->{
            System.out.println(item);
        }));
        //存在值就做什么,不存在值时做什么
        optional.ifPresentOrElse((item->{
            //存在值是打印该值
            System.out.println(item);
        }),()->{
            //不存在值是打印不存在
            System.out.println("不存在,值为null");
        });
    }
}
