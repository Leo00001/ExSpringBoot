package com.baiyuas.boot.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author baiyu
 * <p>
 * Spring 定时器Schedule
 */
@Configuration
@ComponentScan("com.baiyuas.boot.service")
@EnableScheduling
public class ScheduleTaskConfig {

}
