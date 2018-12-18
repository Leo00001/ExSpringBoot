#Spring MVC配置

SpringMVC配置实现`WebMvcConfigurer`接口，同时在类上增加注解`@EnableWebMvc`

### 添加View解析

通过`@Bean`提供给Spring

```
@Bean
public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("WEB-INF/classes/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
}
```

### 配置静态资源映射

实现`addResourceHandlers`方法

```
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/assets/");
}
```

### 配置拦截器

实现`addInterceptors`方法

```
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(provideFormInterceptor())
            .addPathPatterns("/anno/submit_json"); // 指定拦截的请求
}

```

### 配置简单的页面解析

实现`addViewControllers`方法

```
@Override
public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/index").setViewName("index");
}
```

