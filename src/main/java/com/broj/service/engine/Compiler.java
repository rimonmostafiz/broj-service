package com.broj.service.engine;

/**
 * Created by seal on 7/22/2016.
 */
public interface Compiler {
    CompileStatus compile(ProcessBuilder processBuilder, String fileLocation);
}
