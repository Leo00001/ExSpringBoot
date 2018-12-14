package com.baiyuas.boot.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * BeanNameAware
 * BeanFactoryAware
 * ApplicationContextAware
 * MessageSourceAware
 * ApplicationEventPublisherAware
 * ResourceLoaderAware
 */
@Service
public class AwareService implements ResourceLoaderAware, BeanNameAware {

    ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("Inject bean is " + beanName);
    }


    public void printResult() {
        try {

            Resource fileRes = resourceLoader.getResource("classpath:el/test.txt");
            String content = IOUtils.toString(fileRes.getInputStream(), Charset.forName("utf8"));
            System.out.println("Result ==>" + content);
        } catch (Exception e) {
            System.out.println(" print error " + e.getMessage());
        }
    }

}
