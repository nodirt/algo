package algo.structures;

import java.util.Comparator;

public interface PriorityQueue<T> {
    boolean isEmpty();
    T max();
    T extractMax();
    void add(T element);
    int getSize();
    Comparator<T> getComparator();
}
