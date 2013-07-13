package algo.sort;

import org.junit.Test;

import algo.ManyTimes;

public abstract class SortingAlgorithmTests extends SortingTestBase {
    public abstract SortingAlgorithm<Integer> createAlgorithm();
    
    @Test
    @ManyTimes
    public void unique() {
        Integer[] nums = randomOrder();
        createAlgorithm().sort(nums);
        assertSorted(nums);
    }
    
    @Test
    @ManyTimes
    public void duplicates() {
        Integer[] nums = randomIntegers();
        createAlgorithm().sort(nums);
        assertSorted(nums);
    }    

}
