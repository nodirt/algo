package algo.sort.comparison;

public class MergeSort<E> extends ComparisonSorting<E> {
    @Override
    public boolean isStable() {
        return true;
    }

    @Override
    public void sort(final E[] array) {

        class Sorter {
            final Object[] buf = new Object[array.length];

            @SuppressWarnings("unchecked")
            void sort(int low, int high) {
                if (low >= high) {
                    return;
                }

                int middle = low + (high - low) / 2;
                sort(low, middle);
                sort(middle + 1, high);

                if (!greater(array[middle], array[middle + 1])) {
                    // already sorted
                    return;
                }

                int i = low;
                int j = middle + 1;
                int k = 0;
                while (i <= middle && j <= high) {
                    if (greater(array[i], array[j])) {
                        buf[k] = array[j];
                        j++;
                    } else {
                        buf[k] = array[i];
                        i++;
                    }
                    k++;
                }

                if (i <= middle) {
                    int toMove = middle - i + 1;
                    for (i = 0; i < toMove; i++) {
                        array[high - i] = array[middle - i];
                    }
                }

                for (i = 0; i < k; i++) {
                    array[low + i] = (E) buf[i];
                }
            }

        }

        new Sorter().sort(0, array.length - 1);
    }
}
