package com.broj.util;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by seal on 8/3/16.
 */
public class IOUtils {

    public static String fileToString(String path) {
        String str = null;
        try {
            str = Files.toString(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
