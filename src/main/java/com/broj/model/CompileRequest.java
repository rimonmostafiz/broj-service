package com.broj.model;

import com.broj.util.PathUtil;
import lombok.*;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
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
    @Getter(AccessLevel.PRIVATE)
    private String fileName; // like A.java

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private String fileNameWithoutExtension; // like A

    @Setter(AccessLevel.PRIVATE)
    private String parentFilePath;

    /**
     * @param var is 'true' the return String with file extension and
     *            return only file name otherwise.
     * @return file name with or without extension depending on var.
     */
    public String getFileName(boolean var) {
        String f = null;
        if (var) {
            fileName = fileName != null ? fileName :
                    (fileName = PathUtil.fileNameFromPath(sourceFilePath));
            f = fileName;
        } else {
            if (fileNameWithoutExtension == null) {
                String[] strings = getFileName().split("\\.");
                fileNameWithoutExtension = strings[0];
            }
            f = fileNameWithoutExtension;
        }
        return f;
    }

    public String getParentFilePath() {
        return parentFilePath != null ? parentFilePath : (parentFilePath = PathUtil.filePath(sourceFilePath));
    }
}