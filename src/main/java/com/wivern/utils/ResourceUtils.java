package com.wivern.utils;

import java.io.File;

/**
 * @author vkoulakov
 * @since 9/13/16.
 */
public class ResourceUtils {
    public static String withEndSeparator(String path){
        if (path.endsWith(File.separator)){
            return path;
        } else {
            return path + File.separator;
        }
    }
}
