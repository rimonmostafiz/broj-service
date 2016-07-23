package com.broj.service.engine;

/**
 * Created by seal on 7/22/2016.
 */
public class ProcessBuilderFactory {

    public static ProcessBuilder getProcessBuilderForCompile(String language, String fileName, String targetFileName) {
        ProcessBuilder processBuilder = null;
        if (Language.CPP.equalsIgnoreCase(language))
            processBuilder = new ProcessBuilder("g++", fileName, "-o", targetFileName);//not support c++11 so far

        // other language currently omitted

        return processBuilder;
    }

    public static ProcessBuilder getProcessBuilderForExecution(String language, String fileName) {
        ProcessBuilder processBuilder = null;
        if (Language.CPP.equalsIgnoreCase(language))
            processBuilder = new ProcessBuilder("./" + fileName);

        return processBuilder;
    }
}
