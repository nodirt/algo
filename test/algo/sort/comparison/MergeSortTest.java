package algo.sort.comparison;

import algo.sort.SortingAlgorithm;
import algo.sort.SortingAlgorithmTests;

public class MergeSortTest extends SortingAlgorithmTests {

    @Override
    public SortingAlgorithm<Integer> createAlgorithm() {
        return new MergeSort<Integer>();
    }

}
