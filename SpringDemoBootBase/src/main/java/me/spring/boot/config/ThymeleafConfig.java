package me.spring.boot.config;

import org.springframework.boot.autoconfigure.template.TemplateLocation;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

@Configuration
public class ThymeleafConfig {


    private final ThymeleafProperties properties;

    private final ApplicationContext applicationContext;

    ThymeleafConfig(ThymeleafProperties properties,
                    ApplicationContext applicationContext) {
        this.properties = properties;
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void checkTemplateLocationExists() {
        boolean checkTemplateLocation = this.properties.isCheckTemplateLocation();
        if (checkTemplateLocation) {
            TemplateLocation location = new TemplateLocation(
                    this.properties.getPrefix());
            if (!location.exists(this.applicationContext)) {

            }
        }
    }

    @Bean
    public SpringResourceTemplateResolver defaultTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix(this.properties.getPrefix());
        resolver.setSuffix(this.properties.getSuffix());
        resolver.setTemplateMode(this.properties.getMode());
        if (this.properties.getEncoding() != null) {
            resolver.setCharacterEncoding(this.properties.getEncoding().name());
        }
        resolver.setCacheable(this.properties.isCache());
        Integer order = this.properties.getTemplateResolverOrder();
        if (order != null) {
            resolver.setOrder(order);
        }
        resolver.setCheckExistence(this.properties.isCheckTemplate());
        resolver.setUseDecoupledLogic(true);
        return resolver;
    }


}
