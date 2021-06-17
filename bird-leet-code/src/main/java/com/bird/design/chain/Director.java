package com.bird.design.chain;

import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/6/17 17:14
 * @Description 主管 处理请假时间3天以下含三天的请求
 */
public class Director extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getDay() <= 3) {
            //主管的业务逻辑处理
            System.out.println(this.name + "已处理");
        } else {
            //交给下一个职责链处理
            if (this.next != null) {
                this.next.handleRequest(request);
            }
        }
    }
}
