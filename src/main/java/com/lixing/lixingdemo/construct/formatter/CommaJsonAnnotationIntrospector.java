package com.lixing.lixingdemo.construct.formatter;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.lixing.lixingdemo.annotation.CommaJsonFormat;
import org.springframework.stereotype.Component;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/10
 * CommaJsonFormat内省器--运行时类型检查
 */
public class CommaJsonAnnotationIntrospector  extends NopAnnotationIntrospector {
    private static final long serialVersionUID = -4218175326034164350L;

    @Override
    public Object findSerializer(Annotated am) {
        CommaJsonFormat annotation = this._findAnnotation(am, CommaJsonFormat.class);
        if (null != annotation) {
            return CustomJsonFieldSerializer.class;
        }
        return super.findSerializer(am);
    }

    @Override
    public Object findDeserializer(Annotated am) {
        CommaJsonFormat annotation = this._findAnnotation(am, CommaJsonFormat.class);
        if (null != annotation) {
            return CustomJsonFieldDeserializer.class;
        }
        return super.findDeserializer(am);
    }
}
