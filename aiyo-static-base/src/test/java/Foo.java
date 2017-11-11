import java.io.Serializable;

public class Foo implements Serializable{
    private int m;
    private int n;

    public Foo(int m, int n) {
        this.m = m;
        this.n = n;
    }
}
