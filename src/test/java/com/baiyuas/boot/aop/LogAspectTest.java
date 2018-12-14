package com.baiyuas.boot.aop;

import com.baiyuas.boot.config.AopConfig;
import com.baiyuas.boot.service.DemoAnnotationService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author baiyu
 * <p>
 * AnnotationConfigApplicationContext 基于配置类加载Spring,还可以通过ClassPathXmlApplicationContext来接在Spring的xml配置文件
 */
public class LogAspectTest {

    @Test
    public void testLogAspect() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService service = context.getBean(DemoAnnotationService.class);
        service.login();
//        service.getUserInfo();
        context.close();
    }
}
