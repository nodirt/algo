package algo.sort;

import java.util.Comparator;

import algo.util.DefaultComparator;

public class OrderStatistic<E> {
    
    public Comparator<E> comparator = new DefaultComparator<E>();
    
    public E findSmallest(final E[] array, int rank) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int low = 0;
        int high = array.length - 1;
        
        while (low < high) {
            int middle = low + (high - low) / 2;
            E pivot = array[middle];
            int i = low;
            int j = high;
            
            while (i <= j) {
                while (comparator.compare(array[i], pivot) < 0) {
                    i++;
                }
                while (comparator.compare(array[j], pivot) > 0) {
                    j--;
                }
                
                if (i <= j) {
                    E tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    i++;
                    j--;
                }
            }

            int leftPartitionSize = j - low + 1;
            if (rank == leftPartitionSize) {
                return pivot;
            } else if (rank < leftPartitionSize) {
                high = j;
            } else {
                low = i;
                rank -= leftPartitionSize;
            }
        }
        
        return array[low];
    }
}
