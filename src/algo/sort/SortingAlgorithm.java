package algo.sort;

public abstract class SortingAlgorithm<E> extends algo.Algorithm {

    public boolean isStable() {
        return false;
    }

    public abstract void sort(E[] array);
}
