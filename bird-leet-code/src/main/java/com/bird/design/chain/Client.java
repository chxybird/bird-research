package com.bird.design.chain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author lipu
 * @Date 2021/6/17 17:17
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        //模拟请求
        Request request=new Request();
        request.setDate(new Date());
        request.setDay(2);
        request.setUuid(UUID.randomUUID().toString());
        //定义责任链中的具体处理对象
        Director director=new Director();
        director.setName("主管");
        Manager manager=new Manager();
        manager.setName("经理");
        List<Handler> handlerList=new ArrayList<>();
        handlerList.add(director);
        handlerList.add(manager);

        //定义入口处理 并初始化所有职责
        CommonHandler handler=new CommonHandler(handlerList);
        handler.handleRequest(request);

    }
}
