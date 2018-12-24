package me.spring.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author baiyu
 * <p>
 * 类型安全配置
 * <p>
 * 需要添加如下依赖，都则报错
 * <dependency>
 *      <groupId>org.springframework.boot</groupId>
 *      <artifactId>spring-boot-configuration-processor</artifactId>
 *      <optional>true</optional>
 * </dependency>
 */
@Component
@PropertySource(value = "classpath:config/book.yaml", encoding = "UTF-8")
@ConfigurationProperties(prefix = "book2")
public class BookSetting {

    private String author;

    private String name;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
