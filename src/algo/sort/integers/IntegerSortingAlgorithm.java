package algo.sort.integers;

import algo.sort.SortingAlgorithm;
import algo.util.Function;

public abstract class IntegerSortingAlgorithm<E> extends SortingAlgorithm<E> {
    Function<E, Integer> _keyFn;
    
    public Function<E, Integer> getKeyFunction() {
        return _keyFn;
    }
    public void setKeyFunction(Function<E, Integer> func) {
        if (func == null) {
            throw new IllegalArgumentException("func must not be null");
        }
        _keyFn = func;
    }
    
    protected int getKey(E element) {
        return _keyFn.apply(element);
    }
    
    public IntegerSortingAlgorithm(Function<E, Integer> keyFn) {
        setKeyFunction(keyFn);
    }
}
