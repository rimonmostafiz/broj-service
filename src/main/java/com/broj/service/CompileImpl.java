package com.broj.service;

import com.broj.model.CompileRequest;
import com.broj.model.CompileResponse;
import com.broj.service.diff.DiffChecker;
import com.broj.service.engine.Compiler;
import com.broj.service.engine.ProcessBuilderFactory;
import com.broj.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by seal on 7/22/2016.
 */
@Service
public class CompileImpl implements Compile {

    private static final Logger logger = LoggerFactory.getLogger(CompileImpl.class);

    @Autowired
    private Compiler compiler;

    @Autowired
    private DiffChecker diffChecker;

    @Override
    public CompileResponse compile(CompileRequest request) {
        ProcessBuilder processBuilder = ProcessBuilderFactory.getProcessBuilderForCompile(request.getSourceFileType(),
                        request.getFileName(true),
                        request.getFileName(false)); //what would be the target compiled file name ???

        CompileStatus compileStatus = compiler.compile(processBuilder,
                request.getParentFilePath());

        if (compileStatus == CompileStatus.COMPILE_ERROR) {
            logger.info("{}", CompileStatus.COMPILE_ERROR);
            return new CompileResponse(CompileStatus.COMPILE_ERROR);
        }
        logger.info("{}", CompileStatus.COMPILE_SUCCESS);

        ProcessBuilder executeProcess = ProcessBuilderFactory.getProcessBuilderForExecution(request.getSourceFileType(),
                request.getFileName(false));

        CompileStatus executionStatus = compiler.execute(executeProcess,
                request.getParentFilePath(),
                request.getInputFilePath(),
                request.getParentFilePath() + "/out.txt", // output file should be define other way
                request.getTimeLimit());

        logger.info("Execution status {}", executionStatus);
        switch (executionStatus) {
            case EXECUTION_ERROR:
                return new CompileResponse(executionStatus);
            case TIME_LIMIT_EXIT:
                return new CompileResponse(executionStatus);
            case RUN_TIME_ERROR:
                return new CompileResponse(executionStatus);
        }

        String codeOutput = IOUtils.fileToString(request.getParentFilePath() + "/out.txt");
        String output = IOUtils.fileToString(request.getOutputFilePath());
        CompileStatus diffStatus = diffChecker.check(codeOutput, output);
        logger.info("Diff Checker status {}", diffStatus);
        return new CompileResponse(diffStatus);
    }
}
