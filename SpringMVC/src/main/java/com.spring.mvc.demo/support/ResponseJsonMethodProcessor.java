package com.spring.mvc.demo.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mvc.demo.annotation.ResponseJson;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author baiyu
 * <p>
 * 自定义返回jsonHandlerMethodReturnValueHandler
 */
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
