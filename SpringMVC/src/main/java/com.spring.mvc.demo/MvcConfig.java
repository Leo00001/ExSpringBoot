package com.spring.mvc.demo;


import com.spring.mvc.demo.interceptor.FormInterceptor;
import com.spring.mvc.demo.support.ResponseJsonMethodProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * @author baiyu
 * <p>
 * MVC Config
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.mvc.demo")
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public FormInterceptor provideFormInterceptor() {
        return new FormInterceptor();
    }

    @Bean
    public ResponseJsonMethodProcessor provideResponseJsonMethodProcessor() {
        return new ResponseJsonMethodProcessor();
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(provideResponseJsonMethodProcessor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(provideFormInterceptor())
                .addPathPatterns("/anno/submit_json")
                .addPathPatterns("/rest/get_json")
                .addPathPatterns("/anno/exception");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
