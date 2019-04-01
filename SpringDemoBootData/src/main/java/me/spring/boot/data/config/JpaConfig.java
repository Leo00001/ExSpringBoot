package me.spring.boot.data.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author baiyu
 * <p>
 * 数据库配置
 */
@Configuration
@EnableJpaRepositories("me.spring.boot.data.repo")
public class JpaConfig {


}
