package packt.util;

import java.util.List;
import java.util.ServiceLoader;

public interface SortUtil {

    public <T extends Comparable> List<T> sortList(List<T> list);
    public int getIdealMaxInputLength();

    public static Iterable<SortUtil> getAllProviders() {
        return ServiceLoader.load(SortUtil.class);
    }

    public static SortUtil getProviderInstance(int listSize) {
        Iterable<SortUtil> sortUtils = ServiceLoader.load(SortUtil.class);
        for (SortUtil sortUtil : sortUtils) {
            if (listSize < sortUtil.getIdealMaxInputLength()) {
                return sortUtil;
            }
        }
        return null;
    }
}