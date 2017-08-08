package space.aiyo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.annotation.*;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO  ParameterAnnotation
 * Created by Yo on 2017/7/3.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TypeAnnotation
public class AnnotationTest {
    @FiledAnnotation
    private String str1;

    @ConstructorAnnotation
    public AnnotationTest() {
    }

    @Test
    @MethodAnnotation
    public void tst() {
        str1 = "dsdsd";
        test2(str1);
        Class clazz = AnnotationTest.class;
        //成员变量注解
        Field [] fields = clazz.getDeclaredFields();
        @LocalVeriableAnnotation
        List<Field> list = new ArrayList<>();
        for (Field field:fields){
            if(field.getAnnotationsByType(FiledAnnotation.class)!=null){
                list.add(field);
                System.out.println(field.getName());
            }
        }

        //方法注解
        for (Method method : clazz.getMethods()) {
//            System.out.println(method.toString());
//            System.out.println(method.toGenericString());
            MethodAnnotation testAnnotation = method.getAnnotation(MethodAnnotation.class);
            if (testAnnotation != null) {
                System.out.println(testAnnotation.str());
                System.out.println(testAnnotation.intValue());
            }

        }
    }

    public void test2( String str){
        System.out.println(str);
    }

}
