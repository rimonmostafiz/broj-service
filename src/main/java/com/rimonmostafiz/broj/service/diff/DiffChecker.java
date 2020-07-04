package com.rimonmostafiz.broj.service.diff;

import com.rimonmostafiz.broj.util.CompileStatus;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
public interface DiffChecker {

    CompileStatus check(String codeOutput, String output);
}
