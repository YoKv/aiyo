package space.aiyo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 本地变量 或catch语句
 * Created by Yo on 2017/7/3.
 */

@Target(ElementType.LOCAL_VARIABLE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalVeriableAnnotation {
    String str() default "str";
    int intValue() default 0;

}
