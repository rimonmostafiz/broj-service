package com.broj.service.engine;

import com.broj.service.CompileStatus;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
public interface Compiler {

    CompileStatus compile(ProcessBuilder processBuilder, String fileLocation);

    CompileStatus execute(ProcessBuilder processBuilder, String fileLocation,
                          String inputFilePath, String outputFilePath,
                          long timeInMillis);
}
