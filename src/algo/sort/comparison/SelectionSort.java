package algo.sort.comparison;

import static algo.util.Util.*;

public class SelectionSort<E> extends ComparisonSorting<E> {
    @Override
    public void sort(E[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }
}
