package com.bird.stream.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author lipu
 * @Date 2021/1/23 13:49
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String className;
}
