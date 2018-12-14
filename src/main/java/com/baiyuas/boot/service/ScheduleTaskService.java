package com.baiyuas.boot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author baiyu
 * <p>
 * 定时任务
 */
@Service
public class ScheduleTaskService {


    /**
     * 每隔5s执行一次
     */
    @Scheduled(fixedRate = 5000)
    public void printTimePer5s() {
        LocalDateTime dateTime = LocalDateTime.now();
        String now = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Thread thread = Thread.currentThread();
        System.out.println("Thread name:" + thread.getName() + " id:" + thread.getId() + " " + now);
    }

    /**
     * 每隔10s执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void printScheduleCron() {
        LocalDateTime dateTime = LocalDateTime.now();
        String now = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Thread thread = Thread.currentThread();
        System.out.println("Thread name:" + thread.getName() + " id:" + thread.getId() + " " + "Spring Schedule execute by corn '0/10 * * * * ?' now:" + now);
    }

}
