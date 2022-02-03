package com.lixing.lixingdemo.fileExporter;

import lombok.Data;

import java.io.Serializable;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-14 15:41
 */
@Data
public class FileExporterInputDTO implements Serializable{
    private static final long serialVersionUID = 9119886620726434938L;

    private Integer pageIndex;

    private Integer pageSize;

    private String fileName;
}
