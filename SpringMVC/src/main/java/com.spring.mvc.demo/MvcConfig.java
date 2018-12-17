package com.spring.mvc.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author baiyu
 * <p>
 * MVC Config
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.mvc.demo")
public class MvcConfig {



    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
