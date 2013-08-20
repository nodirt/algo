package algo;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CombinatoricsTests extends BaseTestClass {
    @Test
    public void allSubsetsTest() {
        Integer[] input = { 0, 1, 2 };
        Queue<Integer[]> allSubsequences = new LinkedList<Integer[]>();
        for (Integer[] subseq : Combinatorics.allSubsequences(input)) {
            allSubsequences.add(subseq);
        }
        
        assertArrayEquals(new Integer[0], allSubsequences.remove());
        assertArrayEquals(new Integer[] { 0 }, allSubsequences.remove());
        assertArrayEquals(new Integer[] { 1 }, allSubsequences.remove());
        assertArrayEquals(new Integer[] { 0, 1 }, allSubsequences.remove());
        
        assertArrayEquals(new Integer[] { 2 }, allSubsequences.remove());
        assertArrayEquals(new Integer[] { 0, 2 }, allSubsequences.remove());
        assertArrayEquals(new Integer[] { 1, 2 }, allSubsequences.remove());
        assertArrayEquals(new Integer[] { 0, 1, 2 }, allSubsequences.remove());
    }
}
