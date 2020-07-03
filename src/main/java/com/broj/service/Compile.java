package com.broj.service;

import com.broj.model.CompileRequest;
import com.broj.model.CompileResponse;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
public interface Compile {

    CompileResponse compile(CompileRequest request);

}
