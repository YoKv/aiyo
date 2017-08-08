package space.aiyo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类，接口，枚举的注解，不能用于注解
 * Created by Yo on 2017/7/3.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeAnnotation {
    String str() default "str";
    int intValue() default 0;

}
