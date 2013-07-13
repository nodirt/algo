package algo.sort.comparison;

import algo.sort.SortingAlgorithm;
import algo.sort.SortingAlgorithmTests;

public class HeapSortTest extends SortingAlgorithmTests {
    @Override
    public SortingAlgorithm<Integer> createAlgorithm() {
        return new HeapSort<Integer>();
    }
}
