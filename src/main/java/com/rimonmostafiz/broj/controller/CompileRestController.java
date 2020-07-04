package com.rimonmostafiz.broj.controller;

import com.rimonmostafiz.broj.model.CompileRequest;
import com.rimonmostafiz.broj.model.CompileResponse;
import com.rimonmostafiz.broj.service.compiler.Compile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class CompileRestController {

    private final Compile compile;

    @PostMapping(value = "/compile")
    public ResponseEntity<?> compileRequest(@RequestBody CompileRequest compileRequest) {
        log.info("Hit in controller, {}", compileRequest);
        CompileResponse response = compile.compile(compileRequest);
        return ResponseEntity.ok(response);
    }
}
