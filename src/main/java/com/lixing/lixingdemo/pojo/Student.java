package com.lixing.lixingdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-18 11:03
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student implements Serializable {

    private Long stuNo;

    private String name;

    private Integer age;
}
