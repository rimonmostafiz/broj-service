package com.broj.controller;

import com.broj.model.CompileRequest;
import com.broj.model.CompileResponse;
import com.broj.service.Compile;
import com.broj.service.engine.CompileStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by seal on 7/22/2016.
 */
@RestController
public class CompileRestController {

    private static final Logger logger = LoggerFactory.getLogger(CompileRestController.class);

    @Autowired
    private Compile compile;

    @ResponseBody
    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public ResponseEntity<?> compileRequest(@RequestBody CompileRequest compileRequest) {
        logger.info("userName {}, srcType {}, srcFile {}, inputFile {}, outputFile {}",
                compileRequest.getUserName(), compileRequest.getSourceFileType(),
                compileRequest.getSourceFilePath(), compileRequest.getInputFilePath(),
                compileRequest.getOutputFilePath());

        CompileResponse response = compile.compile(compileRequest);

        return ResponseEntity.ok(response);
    }
}
