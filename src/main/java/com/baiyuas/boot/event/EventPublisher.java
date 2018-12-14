package com.baiyuas.boot.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author baiyu
 * <p>
 * Spring 事件，第三步，发布者发布消息
 */
@Component
public class EventPublisher {

    @Autowired
    private ApplicationContext context;

    public void publish(String msg) {
        System.out.println("This msg from publisher " + msg);
        context.publishEvent(new MessageEvent(this, msg));
    }
}
