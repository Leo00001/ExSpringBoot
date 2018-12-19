package com.spring.mvc.demo.annotation;

import java.lang.annotation.*;

/**
 * @author baiyu
 * <p>
 * 指定方法返回json
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseJson {
}
