package com.wivern.themes;

import com.wivern.utils.ResourceUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.context.Theme;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.templateresource.SpringResourceTemplateResource;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Map;

/**
 * @author vkoulakov
 * @since 9/13/16.
 */
public class SpringThemeResourceTemplateResolver extends SpringResourceTemplateResolver {

    private ApplicationContext applicationContext;

    public SpringThemeResourceTemplateResolver() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, String resourceName, String characterEncoding, Map<String, Object> templateResolutionAttributes) {
        Theme theme = ThemeHolder.getTheme();
        if (theme != null) {
            try {
                String location = theme.getMessageSource().getMessage("templates", null, LocaleContextHolder.getLocale());
                SpringResourceTemplateResource resource = new SpringResourceTemplateResource(this.applicationContext,
                        ResourceUtils.withEndSeparator(location) + template + this.getSuffix(), characterEncoding);
                if (resource.exists()) {
                    return resource;
                }
            } catch (NoSuchMessageException ignored) {
            }
        }
        return super.computeTemplateResource(configuration, ownerTemplate, template, resourceName, characterEncoding, templateResolutionAttributes);
    }
}
