package com.baiyuas.boot.biz;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrePostBean {

    public PrePostBean() {
        System.out.println("This is pre post bean constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("This is pre post bean init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("This is pre post bean destroy");
    }

}
