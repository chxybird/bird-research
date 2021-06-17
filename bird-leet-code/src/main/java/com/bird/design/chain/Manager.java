package com.bird.design.chain;

/**
 * @Author lipu
 * @Date 2021/6/17 17:19
 * @Description 经理 处理请假天数大于3天的
 */
public class Manager extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (request.getDay() > 3) {
            System.out.println(this.name + "已处理");
        } else {
            //交给下一个职责链处理
            if (this.next != null) {
                this.next.handleRequest(request);
            }
        }
    }
}
