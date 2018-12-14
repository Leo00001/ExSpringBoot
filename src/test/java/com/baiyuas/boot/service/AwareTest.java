package com.baiyuas.boot.service;

import com.baiyuas.boot.config.AwareConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AwareConfig.class})
public class AwareTest {


    @Autowired
    private AwareService service;

    @Test
    public void testAware() {
        service.printResult();
    }
}
