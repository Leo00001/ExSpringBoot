package com.baiyuas.boot.event;

import com.baiyuas.boot.config.EventConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EventTest {

    @Test
    public void testEvent() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        EventPublisher publisher = context.getBean(EventPublisher.class);
        publisher.publish("Leo go school");
        context.close();
    }
}
