package com.baiyuas.boot.config;

import com.baiyuas.boot.annotation.ScanConfiguration;
import com.baiyuas.boot.conditional.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;

/**
 * @author baiyu
 * <p>
 * Learn Spring Conditional注解
 */
@ScanConfiguration(value = {@ComponentScan("com.baiyuas.boot.annotation"), @ComponentScan("com.baiyuas.boot.conditional")})
public class ConditionalConfig {


    @Bean
    @Conditional(WindowsCmdCondition.class)
    public ListCommand getWindowCmd() {
        return new WindowsListCmd();
    }

    @Bean
    @Conditional(LinuxCmdCondition.class)
    public ListCommand getLinuxCmd() {
        return new LinuxListCmd();
    }
}
