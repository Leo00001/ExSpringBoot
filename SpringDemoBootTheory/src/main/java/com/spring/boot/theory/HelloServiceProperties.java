package com.spring.boot.theory;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {


    private String content = "world";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
