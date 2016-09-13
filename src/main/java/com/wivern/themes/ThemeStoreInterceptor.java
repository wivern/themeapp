package com.wivern.themes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.context.Theme;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author vkoulakov
 * @since 9/13/16.
 */
public class ThemeStoreInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ThemeStoreInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Theme theme = RequestContextUtils.getTheme(request);
        if (theme != null){
            logger.debug("Theme stored: " + theme.getName());
            ThemeHolder.setTheme(theme);
        }
        return true;
    }
}
