package algo.sort;

import java.util.Comparator;

import algo.Algorithm;
import algo.util.*;
import static algo.util.Util.*;

public class OrderStatistic<E> extends Algorithm {
    
    public Comparator<E> comparator = new DefaultComparator<E>();
    
    public E findSmallest(final E[] array, int rank) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        
        int low = 0;
        int high = array.length - 1;
        
        while (low < high) {
            E pivot = array[high];
            
            int edge = low;
            
            for (int i = low; i < high; i++) {
                if (comparator.compare(array[i], pivot) <= 0) {
                    swap(array, i, edge);
                    edge++;
                }
            }
            
            swap(array, edge, high);
            
            int smallerElementCount = edge - low;
            if (rank == smallerElementCount) {
                return pivot;
            } else if (rank < smallerElementCount) {
                high = edge - 1;
            } else {
                low = edge + 1;
                rank -= smallerElementCount + 1;
            }
        }
        
        assert rank == 0;
        
        return array[low];
    }
}
