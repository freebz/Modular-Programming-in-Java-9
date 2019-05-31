package packt.util;

import java.util.*;

public class SortUtil {
    private BubbleSortUtilImpl sortImpl = new BubbleSortUtilImpl();
    public <T extends Comparable> List<T> sortList(List<T> list) {
        return this.sortImpl.sortList(list);
    }
}