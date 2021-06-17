package com.bird.design.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lipu
 * @Date 2021/6/17 17:49
 * @Description
 */
public class CommonHandler extends Handler {

    @Override
    public void handleRequest(Request request) {
        if (this.next != null) {
            this.next.handleRequest(request);
        }
    }

    /**
     * @Author lipu
     * @Date 2021/6/17 18:12
     * @Description 初始化职责链
     */
    public CommonHandler(List<Handler> handlerList) {
        Handler p = this;
        for (Handler handler : handlerList) {
            p.next = handler;
            p = p.next;
        }
    }
}
