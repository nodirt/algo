package algo.sort.comparison;

import static algo.util.Util.*;

public class QuickSort<E> extends ComparisonSorting<E> {

    @Override
    public void sort(final E[] array) {

        class Sorter {
            void sort(int low, int high) {
                if (low >= high) {
                    return;
                }

                int middle = low + (high - low) / 2;
                E pivot = array[middle];
                int i = low;
                int j = high;

                while (i <= j) {
                    while (less(array[i], pivot)) {
                        i++;
                    }
                    while (greater(array[j], pivot)) {
                        j--;
                    }

                    if (i <= j) {
                        swap(array, i, j);
                        i++;
                        j--;
                    }
                }

                if (low < j) {
                    sort(low, j);
                }
                if (i < high) {
                    sort(i, high);
                }
            }
        }

        new Sorter().sort(0, array.length - 1);
    }
}
