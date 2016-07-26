package com.broj.model;

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
}