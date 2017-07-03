package space.aiyo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用于字段，包含枚举值
 * 成员变量
 * Created by Yo on 2017/7/3.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FiledAnnotation {
    String str() default "str";
    int intValue() default 0;
}
