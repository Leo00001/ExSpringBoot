package com.baiyuas.boot.biz;

public class NormalBean {

    public NormalBean() {
        System.out.println("This is normal bean constructor");
    }

    public void init() {
        System.out.println("This is normal bean init");
    }

    public void destroy() {
        System.out.println("This is normal bean destroy");
    }


}
