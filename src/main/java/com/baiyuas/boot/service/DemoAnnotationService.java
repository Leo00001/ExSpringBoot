package com.baiyuas.boot.service;


import com.baiyuas.boot.aop.annotation.LogAction;
import org.springframework.stereotype.Service;

/**
 * @author baiyu
 * <p>
 * Aop测试类
 */
@Service
public class DemoAnnotationService {

    @LogAction(name = "login function")
    public void login() {
        System.out.println("You request login");
    }


    public void getUserInfo() {
        System.out.println("This is get User info function");
    }
}
