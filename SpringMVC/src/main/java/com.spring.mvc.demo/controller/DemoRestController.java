package com.spring.mvc.demo.controller;

/**
 * @author baiyu
 * <p>
 * Learn @RestController
 */

import com.spring.mvc.demo.biz.MvcDemoBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class DemoRestController {

    @RequestMapping(value = "/get_json", produces = "application/json;charset=utf-8")
    public MvcDemoBean getJson() {
        MvcDemoBean bean = new MvcDemoBean();
        bean.setId("1");
        return bean;
    }

    @RequestMapping(value = "/get_xml", produces = "application/xml;charset=utf8")
    public MvcDemoBean getXml() {
        MvcDemoBean bean = new MvcDemoBean();
        bean.setId("2");
        bean.setName(bean.getName() + "zz");
        return bean;
    }
}
