package algo.sort;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import algo.BaseTestClass;

public abstract class SortingTestBase extends BaseTestClass {
    protected static final int mMaximum = 100;
    protected static final int mCount = 100;
    
    <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].compareTo(array[i]) > 0) {
                return false;
            }
        }
        return true;
    }
    
    <T extends Comparable<T>> void assertSorted(T[] array) {
        assertTrue(isSorted(array));
    }
    
    public Integer[] randomOrder() {
        Integer[] nums = new Integer[mCount];
        for (int i = 0; i < mCount; i++) {
            nums[i] = i; 
        }
        RandomSorting<Integer> rand = new RandomSorting<Integer>();
        rand.sort(nums);
        return nums;
    }
    
    public Integer[] randomIntegers() {
        Integer[] nums = new Integer[mCount];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(mMaximum);
        }
        return nums;
    }

}
