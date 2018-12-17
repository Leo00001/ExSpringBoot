package com.baiyuas.boot.schedule;

import com.baiyuas.boot.config.ScheduleTaskConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScheduleTaskTest {

    @Test
    public void testScheduleTask() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduleTaskConfig.class);

        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.close();
    }
}
