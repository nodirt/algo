package algo.sort.comparison;

import algo.sort.SortingAlgorithm;
import algo.sort.SortingAlgorithmTests;

public class QuickSortTest extends SortingAlgorithmTests {

    @Override
    public SortingAlgorithm<Integer> createAlgorithm() {
        return new QuickSort<Integer>();
    }

}
