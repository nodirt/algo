package algo.structures;

import org.junit.Assert;
import org.junit.Test;

import algo.BaseTestClass;
import algo.ManyTimes;
import static algo.util.Util.*;

public class HeapTests extends BaseTestClass {
    @Test
    @ManyTimes
    public void heapsort() {
        Heap<Integer> heap = Heap.from(box(randomIntegers(100)));
        int prev = Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            int max = heap.extractMax();
            Assert.assertTrue(max <= prev);
            prev = max;
        }
    }
}
