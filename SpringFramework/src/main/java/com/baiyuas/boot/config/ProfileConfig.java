package com.baiyuas.boot.config;


import com.baiyuas.boot.biz.ProfileBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author baiyu
 * <p>
 * learn @Profile 可以在Spring加载时候动态配置
 */
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")
    public ProfileBean getDevProfileBean() {
        return new ProfileBean("This from dev");
    }

    @Bean
    @Profile("product")
    public ProfileBean getProductProfileBean() {
        return new ProfileBean("This from product");
    }
}
