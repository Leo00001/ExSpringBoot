package com.baiyuas.boot.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @author baiyu
 * <p>
 * Spring 事件，第二步在需要接收事件的类实现ApplicationListener接口
 */
@Component
public class MessageEventLIstener implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent messageEvent) {
        String msg = messageEvent.getMsg();
        System.out.println("This is Listener receive msg " + msg);
    }
}
