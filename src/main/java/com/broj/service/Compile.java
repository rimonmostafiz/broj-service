package com.broj.service;

import com.broj.model.CompileRequest;
import com.broj.model.CompileResponse;

/**
 * Created by seal on 7/22/2016.
 */
public interface Compile {

    CompileResponse compile(CompileRequest request);

}
