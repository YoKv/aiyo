package box;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CollectionsUtil {

    /**
     * 两个整型列表取并集
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<Integer> union(List<Integer> list1,
                                      List<Integer> list2) {
        List<Integer> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        HashSet<Integer> hs = new HashSet<>(list);
        list.clear();
        list.addAll(hs);
        return list;
    }
}
