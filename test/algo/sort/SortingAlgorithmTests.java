package algo.sort;

import org.junit.Test;

import algo.Repeat;

public abstract class SortingAlgorithmTests extends SortingTestBase {
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
