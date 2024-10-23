package com.lixing.lixingdemo.construct.formatter;

import com.lixing.lixingdemo.annotation.CommaFormat;
import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/10
 */
public class CommaFormatAnnotationFormatterFactory extends EmbeddedValueResolutionSupport implements AnnotationFormatterFactory<CommaFormat> {

    // 指定格式化的Field的类型为String类型
    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<Class<?>>(){{
            add(ArrayList.class);
            //add(String.class);
        }};
    }

    @Override
    public Printer<?> getPrinter(CommaFormat annotation, Class<?> fieldType) {
        CommaFormatter commaFormatter = new CommaFormatter();
        commaFormatter.setSeparator(annotation.value());
        return commaFormatter;
    }

    @Override
    public Parser<?> getParser(CommaFormat annotation, Class<?> fieldType) {
        CommaFormatter commaFormatter = new CommaFormatter();
        commaFormatter.setSeparator(annotation.value());
        return commaFormatter;
    }

}
