package algo.util;

public final class Identity implements Function<Object, Object> {
    public static final Identity SINGLETON = new Identity();
    public static final Function<Integer, Integer> INTEGER = Identity.<Integer>func();

    private Identity() {}

    @SuppressWarnings("unchecked")
    public static <A> Function<A, A> func() {
        return (Function<A, A>) SINGLETON;
    }

    @Override
    public Object apply(Object a) {
        return a;
    }
}
