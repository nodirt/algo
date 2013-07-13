package algo.sort.integers;

import org.junit.Ignore;

import algo.sort.SortingAlgorithm;
import algo.sort.SortingAlgorithmTests;
import algo.util.Identity;

public class RadixSortTest extends SortingAlgorithmTests {
    public RadixSortTest() {
        mMaximum = 5000;
    }
    
    @Override
    @Ignore
    public void unique() {
        super.unique();
    }
    
    
    @Override
    public SortingAlgorithm<Integer> createAlgorithm() {
        return new RadixSort<Integer>(Identity.INTEGER);
    }
}
