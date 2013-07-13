package algo.sort;

public abstract class SortingAlgorithm<E> extends algo.Algorithm {
    
    public boolean isStable() {
        return false;
    }
    
    protected final void swap(E[] array, int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    public abstract void sort(E[] array);
}
