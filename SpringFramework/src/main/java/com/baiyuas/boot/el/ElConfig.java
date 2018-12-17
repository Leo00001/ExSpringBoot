package com.baiyuas.boot.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * @author baiyu
 * <p>
 * Spring el
 */
@Configuration
@ComponentScan("com.baiyuas.boot.el")
@PropertySource("classpath:el/test.properties")
public class ElConfig {

    @Value("This is Base Value")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    @Value("#{anotherComponent.anotherValue}")
    private String anotherValue;

    @Value("classpath:el/test.txt")
    private Resource testFile;

    @Value("https://www.baidu.com")
    private Resource url;

    @Value("${book.name}")
    private String bookName;

    @Autowired
    private Environment environment;

// Spring 5.0 默认可以不用配置
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    public void printAllProperties() {
        try {

            System.out.println("normal: " + normal);
            System.out.println("osName: " + osName);
            System.out.println("anotherValue: " + anotherValue);
            System.out.println("randomNumber: " + randomNumber);

            System.out.println("testUrl: " + url.getURI().toString());
            System.out.println("testFile: " + IOUtils.toString(testFile.getInputStream(), Charset.forName("utf8")));
            System.out.println("book.name: " + bookName);
            System.out.println("book.author: " + environment.getProperty("book.author"));

        } catch (Exception e) {
            System.out.println(" print config value error:" + e.getMessage());
        }
    }

}
