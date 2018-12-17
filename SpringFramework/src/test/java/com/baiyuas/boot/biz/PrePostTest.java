package com.baiyuas.boot.biz;

import com.baiyuas.boot.config.PrePostConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrePostTest {

    @Test
    public void testPrePostConfig() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
        context.getBean(NormalBean.class);
        context.getBean(PrePostBean.class);
        context.close();
    }

}
