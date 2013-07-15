package algo.sort.integers;

import algo.sort.SortingAlgorithm;
import algo.util.Function;

public abstract class IntegerSortingAlgorithm<E> extends SortingAlgorithm<E> {
    Function<E, Integer> mKeyFn;

    public Function<E, Integer> getKeyFunction() {
        return mKeyFn;
    }

    public void setKeyFunction(Function<E, Integer> func) {
        if (func == null) {
            throw new IllegalArgumentException("func must not be null");
        }
        mKeyFn = func;
    }

    protected int getKey(E element) {
        return mKeyFn.apply(element);
    }

    public IntegerSortingAlgorithm(Function<E, Integer> keyFn) {
        setKeyFunction(keyFn);
    }
}
