package base;

import java.io.*;

public class CloneUtil {
    // 用序列化与反序列化实现深克隆
    public static Object deepClone(Object src) {
        Object o = null;
        if (src != null /* && src instanceof Serializable */) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(src);
                oos.close();
                ByteArrayInputStream bais = new ByteArrayInputStream(baos
                        .toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                o = ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

}
