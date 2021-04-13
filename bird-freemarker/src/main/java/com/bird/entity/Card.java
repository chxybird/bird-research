package com.bird.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lipu
 * @Date 2021/4/12 16:08
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;
    private String name;
}
