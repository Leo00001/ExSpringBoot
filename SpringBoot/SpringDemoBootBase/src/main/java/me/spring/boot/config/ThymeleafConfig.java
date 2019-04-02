package me.spring.boot.config;

import org.springframework.boot.autoconfigure.template.TemplateLocation;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.MimeType;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;

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

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(defaultTemplateResolver());
        // templateEngine
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding(this.properties.getEncoding().name());
        resolver.setContentType(
                appendCharset(this.properties.getServlet().getContentType(),
                        resolver.getCharacterEncoding()));
        resolver.setProducePartialOutputWhileProcessing(this.properties
                .getServlet().isProducePartialOutputWhileProcessing());
        resolver.setExcludedViewNames(this.properties.getExcludedViewNames());
        resolver.setViewNames(this.properties.getViewNames());
        // This resolver acts as a fallback resolver (e.g. like a
        // InternalResourceViewResolver) so it needs to have low precedence
        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 5);
        resolver.setCache(this.properties.isCache());


        return resolver;
    }

    private String appendCharset(MimeType type, String charset) {
        if (type.getCharset() != null) {
            return type.toString();
        }
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("charset", charset);
        parameters.putAll(type.getParameters());
        return new MimeType(type, parameters).toString();
    }
}
