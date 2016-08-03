package com.broj.service.diff;

import com.broj.service.CompileStatus;

/**
 * Created by seal on 8/3/16.
 */
public interface DiffChecker {

    CompileStatus check(String codeOutput, String output);
}
