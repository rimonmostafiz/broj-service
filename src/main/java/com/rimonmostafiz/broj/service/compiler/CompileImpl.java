package com.rimonmostafiz.broj.service.compiler;

import com.rimonmostafiz.broj.model.CompileRequest;
import com.rimonmostafiz.broj.model.CompileResponse;
import com.rimonmostafiz.broj.service.diff.DiffChecker;
import com.rimonmostafiz.broj.service.engine.Compiler;
import com.rimonmostafiz.broj.service.engine.ProcessBuilderFactory;
import com.rimonmostafiz.broj.util.CompileStatus;
import com.rimonmostafiz.broj.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CompileImpl implements Compile {

    private static final Logger logger = LoggerFactory.getLogger(CompileImpl.class);

    private final Compiler compiler;

    private final DiffChecker diffChecker;

    private CompileRequest request;

    @Override
    public CompileResponse compile(CompileRequest request) {
        this.request = request;
        boolean flow = true;
        CompileStatus status;
        ProcessBuilder processBuilder = getProcessBuilder(ProcessBuilderFactory.Task.COMPILE);
        ProcessBuilder executeProcess = getProcessBuilder(ProcessBuilderFactory.Task.EXECUTION);

        status = compile(processBuilder);
        logger.info("{}", status);
        if (status != CompileStatus.COMPILE_SUCCESS)
            flow = false;

        if (flow) {
            status = execution(executeProcess);
            logger.info("{}", status);
            if (status != CompileStatus.EXECUTION_SUCCESS)
                flow = false;
        }

        if (flow) {
            status = diffCheck();
            logger.info("{}", status);
        }

        return new CompileResponse(status);
    }

    private ProcessBuilder getProcessBuilder(ProcessBuilderFactory.Task task) {
        ProcessBuilder processBuilder = null;
        if (ProcessBuilderFactory.Task.COMPILE == task) {
            processBuilder = ProcessBuilderFactory.getProcessBuilderForCompile(request.getSourceFileType(),
                    request.getFileName(true),
                    request.getFileName(false));
        } else {
            processBuilder = ProcessBuilderFactory.getProcessBuilderForExecution(request.getSourceFileType(),
                    request.getFileName(false));
        }
        return processBuilder;
    }

    private CompileStatus compile(ProcessBuilder processBuilder) {
        return compiler.compile(processBuilder, request.getParentFilePath());
    }

    private CompileStatus execution(ProcessBuilder processBuilder) {
        return compiler.execute(processBuilder,
                request.getParentFilePath(),
                request.getInputFilePath(),
                request.getParentFilePath() + "/out.txt", // output file should be define other way
                request.getTimeLimit());
    }

    private CompileStatus diffCheck() {
        String codeOutput = IOUtils.fileToString(request.getParentFilePath() + "/out.txt");
        String output = IOUtils.fileToString(request.getOutputFilePath());
        return diffChecker.check(codeOutput, output);
    }
}
