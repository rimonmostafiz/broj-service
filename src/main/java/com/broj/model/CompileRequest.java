package com.broj.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by seal on 7/21/2016.
 */

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CompileRequest {
    private String userName;
    private String sourceFileType;
    private String sourceFilePath;
    private String inputFilePath;
    private String outputFilePath;
}