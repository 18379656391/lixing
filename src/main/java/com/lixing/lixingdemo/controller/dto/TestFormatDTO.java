package com.lixing.lixingdemo.controller.dto;

import com.lixing.lixingdemo.annotation.CommaFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/11
 */
@Data
public class TestFormatDTO implements Serializable {
    private static final long serialVersionUID = -694681320942568829L;

    @CommaFormat
    private ArrayList<String> str;

    @CommaFormat(value = ";")
    private ArrayList<String> str1;
}
