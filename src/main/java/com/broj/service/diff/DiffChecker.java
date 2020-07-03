package com.broj.service.diff;

import com.broj.service.CompileStatus;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
public interface DiffChecker {

    CompileStatus check(String codeOutput, String output);
}
