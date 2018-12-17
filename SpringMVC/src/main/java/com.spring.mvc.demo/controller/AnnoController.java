package com.spring.mvc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author baiyu
 * <p>
 * MVC基本注解使用
 */
@Controller
@RequestMapping("anno")
public class AnnoController {


    /**
     * /@RequestBody返回对象
     *
     * @param request HttpServletRequest
     * @return 返回字符串
     */
    @RequestMapping(produces = "text/plain;charset=utf8")
    @ResponseBody
    public String index(HttpServletRequest request) {

        return "url:" + request.getRequestURI() + " can access";
    }

    /**
     * /@PathVariable 路径变量，使用与get请求url中
     *
     * @param name 路径参数name
     * @return 返回字符串
     */
    @RequestMapping(value = "/path_var/{name}", produces = "text/plain;charset=utf8")
    @ResponseBody
    public String demoPathVar(@PathVariable String name) {
        return "Welcome " + name + " visit our site";
    }

}
