package com.broj.service.impl;

import com.broj.model.CompileRequest;
import com.broj.model.CompileResponse;
import com.broj.service.Compile;
import com.broj.service.engine.CompileStatus;
import com.broj.service.engine.Compiler;
import com.broj.service.engine.ProcessBuilderFactory;
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

    @Override
    public CompileResponse compile(CompileRequest request) {
        ProcessBuilder processBuilder = ProcessBuilderFactory
                .getProcessBuilder(request.getSourceFileType(), request.getSourceFilePath());
        CompileStatus status = compiler.compile(processBuilder, request.getSourceFilePath());
        if (status == CompileStatus.COMPILE_ERROR) {
            return new CompileResponse("COMPILE_ERROR"); // should use enum type
        }
        return null;
    }
}
