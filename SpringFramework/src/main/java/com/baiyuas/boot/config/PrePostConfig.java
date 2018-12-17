package com.baiyuas.boot.config;

import com.baiyuas.boot.biz.NormalBean;
import com.baiyuas.boot.biz.PrePostBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author baiyu
 * <p>
 * test @Bean, @PreDestory @PostConstructor
 */

@Configuration
@ComponentScan("com.baiyuas.boot.biz")
public class PrePostConfig {


    @Bean(initMethod = "init", destroyMethod = "destroy")
    public NormalBean getNormalBean() {
        return new NormalBean();
    }

    @Bean
    public PrePostBean getPrePostBean() {
        return new PrePostBean();
    }

}
