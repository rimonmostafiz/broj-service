package com.broj.service.diff;

import com.broj.service.CompileStatus;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by seal on 8/3/16.
 */
@Service
public class DiffCheckerImpl implements DiffChecker {

    private static final Logger logger = LoggerFactory.getLogger(DiffCheckerImpl.class);

    @Override
    public CompileStatus check(String codeOutput, String output) {
        DiffMatchPatch dmp = new DiffMatchPatch();
        CompileStatus status;

        List<DiffMatchPatch.Diff> list = dmp.diffMain(codeOutput, output);
        if (list.size() == 1 && list.get(0).operation == DiffMatchPatch.Operation.EQUAL)
            status = CompileStatus.ACCEPTED;
        else
            status = CompileStatus.WRONG;

        return status;
    }
}
