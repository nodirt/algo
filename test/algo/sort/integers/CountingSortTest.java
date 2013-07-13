package algo.sort.integers;

import algo.sort.CountingSort;
import algo.sort.SortingAlgorithm;
import algo.sort.SortingAlgorithmTests;
import algo.util.Identity;

public class CountingSortTest extends SortingAlgorithmTests {

    @Override
    public SortingAlgorithm<Integer> createAlgorithm() {
        return new CountingSort<Integer>(maximum, Identity.integer);
    }
    
}
