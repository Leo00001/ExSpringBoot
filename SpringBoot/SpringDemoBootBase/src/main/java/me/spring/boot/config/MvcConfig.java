package me.spring.boot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        System.out.println("MvcConfig-----------------");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("--------");
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/src/main/webapp/WEB-INF/");
//        viewResolver.setViewNames("views/*");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setOrder(2);
//        return viewResolver;
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("views/error");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        // fastjson 替换jackson
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //2.定义一个配置，设置编码方式，和格式化的形式
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        //3.设置成了PrettyFormat格式
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteNullBooleanAsFalse,
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.WriteMapNullValue);
//        //4.处理中文乱码问题
//        List<MediaType> fastMediaTypes =  new ArrayList<>();
//        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastConverter.setSupportedMediaTypes(fastMediaTypes);
//
//        //5.将fastJsonConfig加到消息转换器中
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        // 将fastjson 优先级最高。或者移除jackson 的应用，这样消息转换列表中就只有fastjson
//        converters.add(0, fastConverter);
    }

    //=====Error Config

    @Bean
    public ErrorViewResolver errorViewResolver() {

        return new CustomErrorPageViewResolver();
    }

    private static class CustomErrorPageViewResolver implements ErrorViewResolver {

        @Override
        public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
            return new ModelAndView("error/404r");
        }
    }


}
