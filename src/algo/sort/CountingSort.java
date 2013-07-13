package algo.sort;

import algo.sort.integers.IntegerSortingAlgorithm;
import algo.util.Function;

public class CountingSort<E> extends IntegerSortingAlgorithm<E> {
    int _max;
    
    public int getMax() {
        return _max;
    }
    public void setMax(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Max must be positive");
        }
        _max = max;
    }
    
    public CountingSort(int max, Function<E, Integer> keyFn) {
        super(keyFn);
        setMax(max);
    }
    
    @Override
    public void sort(E[] array) {
        int[] counts = new int[_max];
        @SuppressWarnings("unchecked")
        E[] orig = (E[]) new Object[array.length];
        
        for (int i = 0; i < array.length; i++) {
            orig[i] = array[i];
            counts[getKey(array[i])]++;
        }
        
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        
        for (int i = orig.length - 1; i >= 0; i--) {
            int key = getKey(orig[i]);
            counts[key]--;
            array[counts[key]] = orig[i]; 
        }
    }
}
