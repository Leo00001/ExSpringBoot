package com.spring.boot.theory;

public class HelloService {


    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String sayHello() {
        return "hello " + content;
    }
}
