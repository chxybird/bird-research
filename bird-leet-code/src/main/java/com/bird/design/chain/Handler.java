package com.bird.design.chain;

import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/6/17 17:00
 * @Description
 */
@Data
public abstract class Handler{
    /* 当前处理者的名称 */
    public String name;
    /* 下一个处理者 */
    public Handler next;

    public void setNext(Handler handler){
        this.next=handler;
    }

    public abstract void handleRequest(Request request);
}
