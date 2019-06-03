package packt.lib.external;

public interface LibApi {
    static LibApi createInstance() {
        return new LibApiImpl();
    }
    public void testMethod();
}