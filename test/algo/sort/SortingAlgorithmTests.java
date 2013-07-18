package algo.sort;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

import algo.ManyTimes;
import algo.sort.comparison.*;
import algo.sort.integers.*;
import algo.util.*;

public class SortingAlgorithmTests extends SortingTestBase {

    @DataPoints
    public static SortingAlgorithm<Integer>[] algorithms() {
        @SuppressWarnings("unchecked")
        SortingAlgorithm<Integer>[] result =
                new SortingAlgorithm[] {
                        // comparison sorting
                        new InsertionSort<Integer>(), 
                        new SelectionSort<Integer>(),
                        new QuickSort<Integer>(),
                        new ThreeWayQuickSort<Integer>(),
                        new MergeSort<Integer>(),
                        new HeapSort<Integer>(),

                        // integer sorting
                        new CountingSort<Integer>(NUM_MAX, Identity.INTEGER),
                        new RadixSort<Integer>(Identity.INTEGER),
                        new BucketSort<Integer>(Identity.INTEGER, NUM_MAX)
                    };
        return result;
    }

    @Theory
    @ManyTimes
    public void unique(SortingAlgorithm<Integer> algo) {
        Integer[] nums = randomOrder();
        algo.sort(nums);
        assertSorted(nums);
    }

    @Theory
    @ManyTimes
    public void duplicates(SortingAlgorithm<Integer> algo) {
        Integer[] nums = randomIntegers();
        algo.sort(nums);
        assertSorted(nums);
    }

}
