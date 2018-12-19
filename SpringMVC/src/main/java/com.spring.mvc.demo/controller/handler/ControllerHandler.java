package com.spring.mvc.demo.controller.handler;

import com.spring.mvc.demo.annotation.ResponseJson;
import com.spring.mvc.demo.biz.MvcException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author baiyu
 * <p>
 * 全局的Controller处理
 */
@ControllerAdvice
public class ControllerHandler {

    /**
     * 控制器全局非法阐述异常处理
     * @param request http请求
     * @return 异常类型
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseJson
    public MvcException dealIllegalArgException(HttpServletRequest request) {
        return new MvcException(9001, "非法参数!");
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "全局配置参数");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }


}
