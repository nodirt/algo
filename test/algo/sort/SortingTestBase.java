package algo.sort;

import static org.junit.Assert.assertTrue;

import algo.BaseTestClass;
import algo.util.*;
import static algo.util.Util.*;

public abstract class SortingTestBase extends BaseTestClass {
    protected static final int NUM_MAX = 100;
    protected static final int NUM_COUNT = 100;
    
    <T extends Comparable<T>> void assertSorted(T[] array) {
        assertTrue(isSorted(array));
    }

    Integer[] randomOrder() {
        return box(Util.randomOrder(NUM_COUNT));
    }
    
    Integer[] randomIntegers() {
        return box(Util.randomIntegers(NUM_COUNT, NUM_MAX));
    }
}
