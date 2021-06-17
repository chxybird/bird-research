package com.bird.design.template;

/**
 * @Author lipu
 * @Date 2021/6/17 13:34
 * @Description
 */
public abstract class Game {
    /*初始化*/
    abstract void init();
    /*执行*/
    abstract void execute();
    /*销毁*/
    abstract void destroy();

    /**
     * @Author lipu
     * @Date 2021/6/17 13:39
     * @Description 模板方法 设置为final不能被重写
     */
    public final void start(){
        init();
        execute();
        destroy();
    }
}
