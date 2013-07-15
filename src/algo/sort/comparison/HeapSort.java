package algo.sort.comparison;

import static algo.util.Util.*;

public class HeapSort<E> extends ComparisonSorting<E> {
    @Override
    public void sort(final E[] array) {
        
        class Heap {
            int size = array.length;

            int getLeft(int index) {
                return index * 2;
            }
            int getRight(int index) {
                return index * 2 + 1;
            }
            
            void maxHeapify(int index) {
                while (true) {
                    int left = getLeft(index);
                    int right = getRight(index);
                    int largest = index;
                    if (left < size && compare(array[left], array[largest]) > 0) {
                        largest = left;
                    }

                    if (right < size && compare(array[right], array[largest]) > 0) {
                        largest = right;
                    }

                    if (largest == index) {
                        break;
                    }
                    swap(array, largest, index);
                    index = largest;
                }
            }

            void sort() {
                for (int i = array.length / 2; i >= 0; i--) {
                    maxHeapify(i);
                }

                for (int i = array.length - 1; i > 0; i--) {
                    swap(array, 0, i);
                    size--;
                    maxHeapify(0);
                }
            }
        }

        new Heap().sort();
    }
}
