package space.aiyo.gsonTest;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * Created by Yo on 2017/5/18.
 */
public class GsonTest {

    class BagOfPrimitives {
        private int value1 = 1;
        private String value2 = "abc";
        private transient int value3 = 3;
        BagOfPrimitives() {
            // no-args constructor
        }
    }

    @Test
    public void test(){
        // Serialization
        BagOfPrimitives obj = new BagOfPrimitives();
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(json);
        System.out.println(obj.value1 == obj2.value1);
        System.out.println(obj.value2.equals(obj2.value2));
        System.out.println(obj.value3 == obj2.value3);
    }



}
