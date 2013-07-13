package algo.sort.integers;

import java.util.Arrays;

import algo.util.Function;

public class RadixSort<E> extends IntegerSortingAlgorithm<E> {

	public RadixSort(Function<E, Integer> keyFn) {
        super(keyFn);
    }
	
	@Override
	public boolean isStable() {
	    return true;
	}
	
    @Override
    public void sort(E[] array) {
    	int power = 8;
    	int base = 1 << power;
    	int offset = 0;
    	@SuppressWarnings("unchecked")
        E[] buf = (E[]) new Object[array.length];
    	int[] counts = new int[base];

    	while (true) {
    		boolean end = true;
    		for (int i = 0; i < array.length; i++) {
    			int key = getKey(array[i]) >> offset;
    			if (key > 0) {
    				end = false;
    			}
    			counts[key % base]++;
    			buf[i] = array[i];
    		}
    		if (end) {
    			break;
    		}

    		for (int i = 1; i < counts.length; i++) {
    			counts[i] += counts[i - 1];
    		}

    		for (int i = buf.length - 1; i >= 0; i--) {
    			int key = (getKey(buf[i]) >> offset) % base;
    			counts[key]--;
    			array[counts[key]] = buf[i];
    		}

    		Arrays.fill(counts, 0);

    		offset += power;
    	}
    }
}
