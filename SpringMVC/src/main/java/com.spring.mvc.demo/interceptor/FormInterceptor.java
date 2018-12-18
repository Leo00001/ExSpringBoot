package com.spring.mvc.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author baiyu
 * <p>
 * Learn 拦截器
 * <p>
 * {@link this#preHandle(HttpServletRequest, HttpServletResponse, Object)}
 * 处理请求方法执行前调用
 * <p>
 * {@link this#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)}
 * 处理请求方法执行后调用
 */
public class FormInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(FormInterceptor.class);
    final static String START_TIME = "start_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer logPrint = new StringBuffer();
        logPrint.append("request url : ").append(request.getRequestURI()).append("\n")
                .append("request contentType: ").append(request.getContentType()).append("\n");

        Enumeration<String> paramKeys = request.getParameterNames();
        while (paramKeys.hasMoreElements()) {
            String paramKey = paramKeys.nextElement();
            Object paramValue = request.getParameter(paramKey);
            logPrint.append("request param key ->").append(paramKey)
                    .append("  value ->").append(paramValue).append("\n");
        }

        logger.debug(logPrint.toString());
        long dealRequestStartTime = System.currentTimeMillis();
        request.setAttribute(START_TIME, dealRequestStartTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long dealRequestStartTime = (long) request.getAttribute(START_TIME);
        long dealRequestFinishTime = System.currentTimeMillis();
        long dealRequestTime = (dealRequestFinishTime - dealRequestStartTime) / 1000;
        logger.debug("deal url " + request.getRequestURI() + " use time " + dealRequestTime + "s");
    }
}
