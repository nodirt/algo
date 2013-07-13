package algo.sort.comparison;

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
                    while (compare(array[i], pivot) < 0) {
                        i++;
                    }
                    while (compare(array[j], pivot) > 0) {
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
