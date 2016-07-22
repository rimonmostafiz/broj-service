package com.broj.util;

import java.nio.file.Paths;

/**
 * Created by seal on 7/22/16.
 */
public class PathUtil {

    public static String fileNameFromPath(String path) {
        return Paths.get(path).getFileName().toString();
    }

    public static String filePath(String path) {
        return Paths.get(path).getParent().toString();
    }
}
