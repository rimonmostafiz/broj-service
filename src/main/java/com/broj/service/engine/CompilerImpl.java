package com.broj.service.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by seal on 7/22/2016.
 */
@Service
public class CompilerImpl implements Compiler {

    private static final Logger logger = LoggerFactory.getLogger(CompilerImpl.class);

    @Override
    public CompileStatus compile(ProcessBuilder processBuilder, String fileLocation) {
        logger.info("Code compilation started...");
        boolean compiled = true;
        processBuilder.directory(new File(fileLocation));
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            String temp;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                process.waitFor();
                while ((temp = reader.readLine()) != null) {
                    compiled = false;
                    logger.error("{}", temp);
                }
                if (!compiled) {
                    inputStream.close();
                    return CompileStatus.COMPILE_ERROR;
                }
                inputStream.close();
                return CompileStatus.COMPILE_SUCCESS;
            }
        } catch (IOException | InterruptedException e) {
            logger.info("error at compilation, {}", e);
        }

        return CompileStatus.COMPILE_ERROR;
    }

    @Override
    public CompileStatus execute(ProcessBuilder processBuilder, String fileLocation,
                                 String inputFilePath, String outputFilePath,
                                 long timeInMillis) {
        processBuilder.directory(new File(fileLocation));
        processBuilder.redirectInput(new File(inputFilePath));
        processBuilder.redirectErrorStream(true);
        processBuilder.redirectOutput(new File(outputFilePath));

        try {
            Process process = processBuilder.start();
            if (!process.waitFor(timeInMillis, TimeUnit.MILLISECONDS))
                return CompileStatus.TIME_LIMIT_EXIT;
            int exitCode = process.exitValue();
            if (exitCode != 0)
                return CompileStatus.RUN_TIME_ERROR;
        } catch (Exception e) {
            logger.info("Error in code compilation");
        }

        return CompileStatus.EXECUTION_SUCCESS;
    }
}