package algo.util;

public final class Identity implements Function<Object, Object> {
    public static final Identity singleton = new Identity();
    public static final Function<Integer, Integer> integer = Identity.<Integer>func();
    private Identity() {
    }
    
    @SuppressWarnings("unchecked")
    public static <A> Function<A, A> func() {
        return (Function<A, A>) singleton;
    }
    
    @Override
    public Object apply(Object a) {
        return a;
    }
}