import base.CloneUtil;
import org.junit.Test;

public class CloneTest {
    @Test
    public void test1() {
        Foo foo1 = new Foo(1, 2);
        Foo foo2 = (Foo)CloneUtil.deepClone(foo1);

        System.out.println(foo1.hashCode());
        System.out.println(foo2.hashCode());
        System.out.println(foo1.equals(foo2));
    }


}
