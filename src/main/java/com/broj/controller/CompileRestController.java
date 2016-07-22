package com.broj.controller;

import com.broj.model.CompileRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by seal on 7/22/2016.
 */
@RestController
public class CompileRestController {

    private static final Logger logger = LoggerFactory.getLogger(CompileRestController.class);

    @ResponseBody
    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public ResponseEntity<?> compileRequest(@RequestBody CompileRequest compileRequest) {
        compileRequest.getInputFilePath();
        logger.info("userName {}, srcFile {}, inputFile {}, outputFile {}",
                compileRequest.getUserName(), compileRequest.getSourceFilePath(),
                compileRequest.getInputFilePath(), compileRequest.getOutputFilePath());

        return ResponseEntity.ok(compileRequest);
    }
}
