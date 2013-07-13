package algo.sort;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import algo.BaseTestClass;

public abstract class SortingTestBase extends BaseTestClass {
    protected int maximum = 100;
    protected int count = 100;
    
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
        Integer[] nums = new Integer[count];
        for (int i = 0; i < count; i++) {
            nums[i] = i; 
        }
        RandomSorting<Integer> rand = new RandomSorting<Integer>();
        rand.sort(nums);
        return nums;
    }
    
    public Integer[] randomIntegers() {
        Integer[] nums = new Integer[count];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(maximum);
        }
        return nums;
    }

}
