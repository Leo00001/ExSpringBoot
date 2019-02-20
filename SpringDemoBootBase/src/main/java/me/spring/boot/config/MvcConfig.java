package me.spring.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        System.out.println("MvcConfig-----------------");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("--------");
    }
}
