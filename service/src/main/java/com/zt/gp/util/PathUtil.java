package com.zt.gp.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

public class PathUtil {
    public static String getPath() throws IOException {
        String absolutePath = new ClassPathResource("/").getFile().getAbsolutePath();
        String targetPath = StringUtils.substringBeforeLast(StringUtils.substringBeforeLast(absolutePath, File.separator), File.separator);
        targetPath +=   File.separator + "upload" + File.separator;
        return targetPath;
    }
}
