package com.spring.mvc.demo.controller;

import com.spring.mvc.demo.biz.MvcDemoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * @author baiyu
 * <p>
 * MVC基本注解使用
 */
@Controller
@RequestMapping("anno")
public class AnnoController {

    Logger logger = LoggerFactory.getLogger(AnnoController.class);

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

    /**
     * 普通参数请求
     * 这里请求的参数不可以哈斯基本类型，否则会报错
     * {@link AbstractNamedValueMethodArgumentResolver#handleNullValue(String, Object, Class)}
     * 在处理请求参数时候会判断是否哈斯基本类型
     *
     * @param id   id
     * @param name name
     * @return 字符串
     * @see AbstractNamedValueMethodArgumentResolver
     */
    @RequestMapping("/request_params")
    @ResponseBody
    public String demoRequestParams(Long id, String name) {
        return "We receive request params i2d " + id + ", name: " + name;
    }


    /**
     * 配置多个请求接口到一个方法上
     *
     * @return 返回字符串
     */
    @RequestMapping(value = {"path1", "path2"})
    @ResponseBody
    public String demoMultiPathRequest() {
        return "This is multi url map one request!";
    }

    /**
     * 处理表单
     *
     * @param demo 参数对象
     * @return 返回字符串
     */
    @RequestMapping(value = "submit_json", produces = "application/json;charset=utf8")
    @ResponseBody
    public String demoRequestObj(MvcDemoBean demo) {
        logger.debug(demo.toString());
        return "Submit success";
    }

    /**
     * 异常展示
     *
     * @return nothing
     */
    @RequestMapping(value = "exception", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String demoException() {
        throw new IllegalArgumentException(" 参数异常 ");
    }
}
