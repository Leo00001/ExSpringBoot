package com.baiyuas.boot.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author baiyu
 * <p>
 * Spring 事件，第一步创建一个事件继承ApplicationEvent
 */
public class MessageEvent extends ApplicationEvent {

    private String msg;

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
