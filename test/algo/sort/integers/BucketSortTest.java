package algo.sort.integers;

import algo.sort.SortingAlgorithm;
import algo.sort.SortingAlgorithmTests;
import algo.util.Identity;

public class BucketSortTest extends SortingAlgorithmTests{
    @Override
    public SortingAlgorithm<Integer> createAlgorithm() {
        return new BucketSort<Integer>(Identity.INTEGER, mMaximum);
    }
}
