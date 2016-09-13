package com.wivern.themes;

import org.springframework.ui.context.Theme;
import org.springframework.util.Assert;

/**
 * @author vkoulakov
 * @since 9/13/16.
 */
public class ThemeHolder {
    private static final ThreadLocal<Theme> themeHolder = new ThreadLocal<>();

    public static Theme getTheme(){
        return themeHolder.get();
    }

    public static void setTheme(Theme theme){
        Assert.notNull(theme);
        themeHolder.set(theme);
    }
}
