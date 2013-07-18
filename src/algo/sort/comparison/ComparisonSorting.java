package algo.sort.comparison;

import java.util.Comparator;

import algo.sort.SortingAlgorithm;

public abstract class ComparisonSorting<E> extends SortingAlgorithm<E> {
    private Comparator<E> mComparator = new algo.util.DefaultComparator<E>();

    public Comparator<E> getComparator() {
        return mComparator;
    }

    public void setComparator(Comparator<E> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("comparator must not be null");
        }

        mComparator = comparator;
    }

    protected int compare(E a, E b) {
        return mComparator.compare(a, b);
    }
    
    protected boolean greater(E a, E b) {
        return compare(a, b) > 0;
    }
    protected boolean less(E a, E b) {
        return compare(a, b) < 0;
    }
}
