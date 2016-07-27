package com.broj.model;

import com.broj.util.PathUtil;
import lombok.*;

/**
 * Created by seal on 7/21/2016.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CompileRequest {
    @Setter(AccessLevel.PRIVATE)
    private String userName;

    @Setter(AccessLevel.PRIVATE)
    private String sourceFileType;

    @Setter(AccessLevel.PRIVATE)
    private String sourceFilePath;

    @Setter(AccessLevel.PRIVATE)
    private String inputFilePath;

    @Setter(AccessLevel.PRIVATE)
    private String outputFilePath;

    @Setter(AccessLevel.PRIVATE)
    private long timeLimit;

    @Setter(AccessLevel.PRIVATE)
    private String fileName; // like A.java

    @Setter(AccessLevel.PRIVATE)
    private String fileNameWithoutExtension; // like A

    @Setter(AccessLevel.PRIVATE)
    private String parentFilePath;

    public String getFileName() {
        return fileName != null ? fileName :
                (fileName = PathUtil.fileNameFromPath(sourceFilePath));
    }

    public String getFileNameWithoutExtension() {
        if (fileNameWithoutExtension == null) {
            String[] strings = getFileName().split("\\.");
            fileNameWithoutExtension = strings[0];
        }
        return fileNameWithoutExtension;
    }

    public String getParentFilePath() {
        return parentFilePath != null ? parentFilePath : (parentFilePath = PathUtil.filePath(sourceFilePath));
    }
}