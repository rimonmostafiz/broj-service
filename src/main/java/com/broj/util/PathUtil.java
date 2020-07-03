package com.broj.util;

import java.nio.file.Paths;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
public class PathUtil {

    public static String fileNameFromPath(String path) {
        return Paths.get(path).getFileName().toString();
    }

    public static String filePath(String path) {
        return Paths.get(path).getParent().toString();
    }
}
