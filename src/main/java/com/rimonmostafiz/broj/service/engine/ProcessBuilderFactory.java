package com.rimonmostafiz.broj.service.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
public class ProcessBuilderFactory {

    private static final Logger logger = LoggerFactory.getLogger(ProcessBuilderFactory.class);

    public static ProcessBuilder getProcessBuilderForCompile(String language, String fileName, String targetFileName) {
        ProcessBuilder processBuilder = null;
        if (Language.CPP.equalsIgnoreCase(language))
            processBuilder = new ProcessBuilder("g++", fileName, "-o", targetFileName);//not support c++11 so far
        else if (Language.JAVA.equalsIgnoreCase(language))
            processBuilder = new ProcessBuilder("javac", fileName);

        return processBuilder;
    }

    public static ProcessBuilder getProcessBuilderForExecution(String language, String fileName) {
        ProcessBuilder processBuilder = null;
        if (Language.CPP.equalsIgnoreCase(language))
            processBuilder = new ProcessBuilder("./" + fileName);
        else if (Language.JAVA.equalsIgnoreCase(language))
            processBuilder = new ProcessBuilder("java", "-cp", ".", fileName);

        return processBuilder;
    }

    public enum Task {
        COMPILE,
        EXECUTION
    }

}
