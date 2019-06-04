package packt.util;

import java.util.List;

public interface SortUtil {
    public <T extends Comparable> List<T> sortList(List<T> list);
}