package algo.sort.comparison;

import static algo.util.Util.*;

public class ThreeWayQuickSort<E> extends ComparisonSorting<E>  {

    @Override
    public void sort(final E[] array) {
        
        class Sorter {
            void sort(int low, int high) {
                int middle = low + (high - low) / 2;
                E pivot = array[middle];
                
                int small = low - 1;
                int k = low;
                int big = high + 1;
                
                while (k < big) {
                    int cmp = compare(array[k], pivot);
                    if (cmp < 0) {
                        small++;
                        swap(array, small, k);
                        k++;
                    } else if (cmp == 0) {
                        k++;
                    } else {
                        big--;
                        swap(array, big, k);
                    }
                }
                
                if (low < small) {
                    sort(low, small);
                }
                if (big < high) {
                    sort(big, high);
                }
            }
        }
        
        new Sorter().sort(0, array.length - 1);
    }
}
