package com.bird.design.template;

/**
 * @Author lipu
 * @Date 2021/6/17 13:41
 * @Description
 */
public class FootBall extends Game {
    @Override
    void init() {
        System.out.println("球队选择、初始化球员");
    }

    @Override
    void execute() {
        System.out.println("进行比赛");
    }

    @Override
    void destroy() {
        System.out.println("结束比赛生成结果");
    }
}
