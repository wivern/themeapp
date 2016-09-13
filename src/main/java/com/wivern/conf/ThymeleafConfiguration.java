package com.wivern.conf;

import com.wivern.themes.SpringThemeResourceTemplateResolver;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;

/**
 * @author vkoulakov
 * @since 9/13/16.
 */
@Configuration
public class ThymeleafConfiguration {

    private ThymeleafProperties properties;
    private ApplicationContext applicationContext;

    public ThymeleafConfiguration(ThymeleafProperties properties, ApplicationContext applicationContext) {
        this.properties = properties;
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver defaultTemplateResolver(){
        SpringThemeResourceTemplateResolver resolver = new SpringThemeResourceTemplateResolver();
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix(this.properties.getPrefix());
        resolver.setSuffix(this.properties.getSuffix());
        resolver.setTemplateMode(this.properties.getMode());
        if(this.properties.getEncoding() != null) {
            resolver.setCharacterEncoding(this.properties.getEncoding().name());
        }

        resolver.setCacheable(this.properties.isCache());
        Integer order = this.properties.getTemplateResolverOrder();
        if(order != null) {
            resolver.setOrder(order);
        }

        return resolver;
    }

}
