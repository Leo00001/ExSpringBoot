package com.baiyuas.boot.biz;

import com.baiyuas.boot.config.ProfileConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProfileConfig.class})
@ActiveProfiles("product")
public class ProfileTest {

    @Autowired
    private ProfileBean profileBean;

    @Test
    public void testProfile() {
        Assert.assertEquals("This from product", profileBean.getContent());
    }
}
