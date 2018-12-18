package com.spring.mvc.demo.controller.handler;

import com.spring.mvc.demo.biz.MvcException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author baiyu
 * <p>
 * 全局的Controller处理
 */
@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public MvcException dealIllegalArgException() {
        return new MvcException(9001, "非法参数!");
    }

}
