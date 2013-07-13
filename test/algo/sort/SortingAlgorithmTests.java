package algo.sort;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import algo.BaseTestClass;
import algo.Repeat;

public abstract class SortingAlgorithmTests extends BaseTestClass {
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
    
    Integer[] randomOrder() {
        Integer[] nums = new Integer[count];
        for (int i = 0; i < count; i++) {
            nums[i] = i; 
        }
        RandomSorting<Integer> rand = new RandomSorting<Integer>();
        rand.sort(nums);
        return nums;
    }
    
    Integer[] randomIntegers() {
        Integer[] nums = new Integer[count];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(maximum);
        }
        return nums;
    }
    
    public abstract SortingAlgorithm<Integer> createAlgorithm();
    
    @Test
    @Repeat
    public void unique() {
        Integer[] nums = randomOrder();
        createAlgorithm().sort(nums);
        assertSorted(nums);
    }
    
    @Test
    @Repeat
    public void duplicates() {
        Integer[] nums = randomIntegers();
        createAlgorithm().sort(nums);
        assertSorted(nums);
    }    

}
