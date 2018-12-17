package com.baiyuas.boot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author baiyu
 * <p>
 * 日志切片
 */
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.baiyuas.boot.aop.annotation.LogAction)")
    public void annotationPointCut() {
    }


    @Before("annotationPointCut()")
    public void logActionBefore(JoinPoint joinPoint) {
        System.out.println("This is LogAction before");
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint jp) {
        try {
            System.out.println("This is LogAction Around");
            return jp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("This is LogAction Around");
        return null;
    }

    /**
     * 匹配使用了@LogAction注解的方法
     *
     * @param joinPoint
     */
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("This is LogAction After");
    }

    /**
     * 匹配DemoAnnotationService中的所有方法
     *
     * @param joinPoint
     */
    @Before("execution(* com.baiyuas.boot.service.DemoAnnotationService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("This is DemoAnnotationService before");
    }

}
