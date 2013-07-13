package algo.sort;

import java.util.Random;

public class RandomSorting<T> extends SortingAlgorithm<T> {
    private final Random sRand = new Random();
    
    @Override
    public void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int j = i + sRand.nextInt(array.length - i);
            swap(array, i, j);
        }
    }
}
