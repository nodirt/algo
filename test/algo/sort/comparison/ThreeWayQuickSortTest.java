package algo.sort.comparison;

import algo.sort.SortingAlgorithm;
import algo.sort.SortingAlgorithmTests;
import algo.sort.ThreeWayQuickSort;

public class ThreeWayQuickSortTest extends SortingAlgorithmTests {
    @Override
    public SortingAlgorithm<Integer> createAlgorithm() {
        return new ThreeWayQuickSort<Integer>();
    }
}
