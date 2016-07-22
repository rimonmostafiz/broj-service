package com.broj.service.engine;

/**
 * Created by seal on 7/22/2016.
 */
public class ProcessBuilderFactory {

    public static ProcessBuilder getProcessBuilder(String language, String fileName) {
        ProcessBuilder processBuilder = null;
        if (Language.CPP.equalsIgnoreCase(language))
            processBuilder = new ProcessBuilder("g++", fileName, "-o", fileName);//not support c++11

        // other language currently omitted

        return processBuilder;
    }
}
