package com.lixing.lixingdemo.fileExporter;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-14 16:31
 */
@Data
public class FileExporterOutputDTO<T extends Object> implements Serializable {
    private static final long serialVersionUID = 3064954325357422172L;

    private Integer total;
    private List<T> resultList;
}
