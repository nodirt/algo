package algo.sort.integers;

import java.util.ArrayList;

import algo.util.Function;

public class BucketSort<E> extends IntegerSortingAlgorithm<E> {
	public int min;
	public int max;
	
	public BucketSort(Function<E, Integer> keyFn, int min, int max) {
	    super(keyFn);
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.min = min;
		this.max = max;
	}
    public BucketSort(Function<E, Integer> keyFn, int max) {
        this(keyFn, 0, max);
    }

    @Override
    public boolean isStable() {
        return true;
    }

    void _insertionSort(ArrayList<E> list) {
		for (int i = 1; i < list.size(); i++) {
			E elem = list.get(i);
			int key = getKey(elem);
			int j = i;
			while (j >= 1 && getKey(list.get(j - 1)) > key) {
				list.set(j, list.get(j - 1));
				j--;
			}
			list.set(j, elem);
		}

    }

	@SuppressWarnings("unchecked")
    @Override
	public void sort(E[] array) {
	    ArrayList<E>[] buckets = (ArrayList<E>[]) new ArrayList[array.length]; 

		for (int i = array.length - 1; i >= 0; i--) {
			int key = getKey(array[i]);
			int index = ((key - min) * buckets.length / max) % buckets.length;
			if (buckets[index] == null) {
			    buckets[index] = new ArrayList<E>();
			}
			buckets[index].add(array[i]);
		}
		
		int j = 0;
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] == null) {
				continue;
			}
			_insertionSort(buckets[i]);
			for (E e: buckets[i]) {
				array[j] = e;
				j++;
			}
		}
	}
}
