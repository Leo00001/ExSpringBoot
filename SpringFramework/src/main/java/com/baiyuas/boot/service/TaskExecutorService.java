package com.baiyuas.boot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author baiyu
 */
@Service
public class TaskExecutorService {

    @Async
    public void executeAsyncTask(Integer i) {
        Thread current = Thread.currentThread();
        System.out.println(current.getId() + " " + current.getName() + " This is execute AsyncTask" + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        Thread current = Thread.currentThread();
        System.out.println(current.getId() + " " + current.getName() + " This is execute AsyncTaskPlus" + i);
    }
}
