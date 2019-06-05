package pattern.four.external;

import java.util.ServiceLoader;

import pattern.four.external.LibInterface;

public class Util {

    public void utilMethod() {
        Iterable<LibInterface> libInstances = ServiceLoader.load(LibInterface.class);
        for (LibInterface libInstance : libInstances) {
            libInstance.publicApi();
        }
    }
    
}