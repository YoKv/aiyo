package space.aiyo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.annotation.TestAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yo on 2017/7/3.
 */

@RunWith(SpringRunner.class)
@SpringBootTest

public class AnnotationTest {
    @TestAnnotation
    private String str;

    @Test
    public void tst() {
        str = "dsdsd";
        Class clazz = AnnotationTest.class;
        //成员变量注解
        Field [] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<>();
        for (Field field:fields){
            if(field.getAnnotationsByType(TestAnnotation.class)!=null){
                list.add(field);
                System.out.println(field.getName());
                System.out.println(field.getAnnotations());
                System.out.println(field.getType());
                System.out.println(field.getDeclaredAnnotations());
            }
        }

        //方法注解
//        for (Method method : clazz.getMethods()) {
////            System.out.println(method.toString());
////            System.out.println(method.toGenericString());
//            TestAnnotation testAnnotation = method.getAnnotation(TestAnnotation.class);
//            if (testAnnotation != null) {
//                System.out.println(testAnnotation.printStr());
//                System.out.println(testAnnotation.printStr2());
//            }
//
//        }
    }

}
