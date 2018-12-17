package com.baiyuas.boot.aop.annotation;

import java.lang.annotation.*;

/**
 * @author baiyu
 * <p>
 * 日志拦截注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAction {

    String name();
}
