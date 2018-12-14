package com.baiyuas.boot.task;

import com.baiyuas.boot.config.AsyncTaskConfig;
import com.baiyuas.boot.service.TaskExecutorService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TaskExecutorTest {


    @Test
    public void testTaskExecutor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncTaskConfig.class);

        TaskExecutorService service = context.getBean(TaskExecutorService.class);
        for (int i = 0; i < 10; i++) {
            service.executeAsyncTask(i);
            service.executeAsyncTaskPlus(i);
        }
//        context.close();
    }

}
