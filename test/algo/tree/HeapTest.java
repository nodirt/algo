package algo.tree;

import java.util.Random;

import org.junit.*;

import static org.junit.Assert.*;
import algo.BaseTestClass;
import algo.ManyTimes;
import static algo.util.Util.*;

public class HeapTest extends BaseTestClass {
    
    void assertValidHeap(Heap<Integer> heap) {
        Integer[] nums = heap.mItems;
        int size = heap.mSize;
        
        for (int i = 0; i < size / 2; i++) {
            int left = i * 2;
            int right = left + 1;
            
            if (left < size) {
                assertTrue(nums[i] >= nums[left]);
                if (right < size) {
                    assertTrue(nums[i] >= nums[right]);
                }
            }
        }
    }
    
    @Test
    @ManyTimes
    public void testHeap() {
        Random rand = new Random();
        int n = 10;
        Heap<Integer> heap = new Heap<Integer>(box(randomIntegers(n)));
        assertValidHeap(heap);
        
        for (int i = 0; i < 100; i++) {
            boolean extract = (heap.size() > 0 && rand.nextBoolean()) || heap.isFull();
            
            if (extract) {
                heap.extractMax();
            } else {
                heap.put(rand.nextInt(n));
            }
            
            assertValidHeap(heap);
        }
    }
}
