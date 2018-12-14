package com.baiyuas.boot.el;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ElTest {

    @Test
    public void testEl() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
        ElConfig config = context.getBean(ElConfig.class);
        config.printAllProperties();
    }
}
