package com.baiyuas.boot.conditional;

import com.baiyuas.boot.config.ConditionalConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionalTest {


    @Test
    public void testConditional() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionalConfig.class);


        ListCommand listCmd = context.getBean(ListCommand.class);
        listCmd.showListCmd();
        context.close();
    }

}
