package algo;

public abstract class Algorithm {
    
    protected final <E> void swap(E[] array, int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    

}
