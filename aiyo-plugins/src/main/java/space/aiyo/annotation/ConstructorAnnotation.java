package space.aiyo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 构造方法
 * Created by Yo on 2017/7/3.
 */

@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstructorAnnotation {
    String str() default "str";
    int intValue() default 0;

}
