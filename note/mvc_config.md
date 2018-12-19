#Spring MVC配置

SpringMVC配置实现`WebMvcConfigurer`接口，同时在类上增加注解`@EnableWebMvc`

[示例](https://github.com/Leo00001/SpringDemo/tree/master/SpringMVC)

## 添加View解析

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

## 配置静态资源映射

实现`addResourceHandlers`方法

```
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:/assets/");
}
```

## 配置拦截器

实现`addInterceptors`方法

```
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(provideFormInterceptor())
            .addPathPatterns("/anno/submit_json"); // 指定拦截的请求
}

```

## 配置简单的页面解析

实现`addViewControllers`方法

```
@Override
public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/index").setViewName("index");
}
```

## 配置控制器全局处理

通过使用@ControllerAdvice注解可以将控制器的全局配置放置在一个类里

```
@ControllerAdvice
public class ControllerHandler {

    /**
     * 控制器全局非法阐述异常处理
     * 实现返回json {@link org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor#getProducibleMediaTypes(HttpServletRequest, Class, Type)}
     * @param request http请求
     * @return 异常类型
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public MvcException dealIllegalArgException(HttpServletRequest request) {
        Set<MediaType> mediaTypes = new LinkedHashSet<>();
        mediaTypes.add(new MediaType("application", "json", Charset.forName("utf8")));
        request.setAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE, mediaTypes);
        return new MvcException(9001, "非法参数!");
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "全局配置参数");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {

    }
}
```

### 关于 _@ExceptionHandler_

我们可以处理全局的异常返回指定页面，也可以返回json, xml,我这里使用@ResponseBody返回的都是xml
如果返回json，可以通过自定义*HandlerMethodReturnValueHandler*自己处理返回值转化为json

**首先我们定义一个注解用于配置在方法上，说明方法返回的是json**

```
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseJson {
}


```

**自定义`ResponseJsonMethodProcessor`实现`HandlerMethodReturnValueHandler`接口，将返回值转换为json输出到前端**

```
public class ResponseJsonMethodProcessor implements HandlerMethodReturnValueHandler {

    ObjectMapper om = Jackson2ObjectMapperBuilder.json().build();

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseJson.class) ||
                returnType.hasMethodAnnotation(ResponseJson.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        if (response != null) {
            response.addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().append(om.writeValueAsString(returnValue));
        }
    }
}

```
**将自定义的`ResponseJsonMethodProcessor`添加到Spring中**

```
@Configuration
@EnableWebMvc
@ComponentScan("com.spring.mvc.demo")
public class MvcConfig implements WebMvcConfigurer {
    ...
    
    @Bean
    public ResponseJsonMethodProcessor provideResponseJsonMethodProcessor() {
        return new ResponseJsonMethodProcessor();
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(provideResponseJsonMethodProcessor());
    }

    ....
}

```

这样就完成了配置，如果需要方法返回json就可以在方法上使用注解@ResponseJson

```
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseJson
    public MvcException dealIllegalArgException() {
        return new MvcException(9001, "非法参数!");
    }

```


### 关于WebDataBinder

