package algo.util;

import java.util.Comparator;

public class DefaultComparator<T> implements Comparator<T> {
    @SuppressWarnings("unchecked")
    @Override
    public int compare(T lhs, T rhs) {
        Comparable<T> comparable;
        if (lhs instanceof Comparable<?>) {
            comparable = (Comparable<T>) (Object) lhs;
            return comparable.compareTo(rhs);
        } else if (rhs instanceof Comparable<?>) {
            comparable = (Comparable<T>) rhs;
            return -comparable.compareTo(lhs);
        }

        throw new UnsupportedOperationException("Parameters are not comparable");
    }
}
