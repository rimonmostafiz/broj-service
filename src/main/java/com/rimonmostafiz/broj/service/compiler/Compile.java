package com.rimonmostafiz.broj.service.compiler;

import com.rimonmostafiz.broj.model.CompileRequest;
import com.rimonmostafiz.broj.model.CompileResponse;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
public interface Compile {

    CompileResponse compile(CompileRequest request);

}
