package com.baiyuas.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author baiyu
 * <p>
 * Spring配置类
 */
@Configuration
@ComponentScan("com.baiyuas.boot")
@EnableAspectJAutoProxy
public class AopConfig {
}
