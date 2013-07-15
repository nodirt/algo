package algo.tree;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.experimental.theories.*;

import algo.ManyTimes;
import algo.util.RandomUtil;

public class BalancedBinarySearchTreeTests {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @DataPoints
    AbstractBalancedBinarySearchTree<Integer, Integer, ?>[] trees() {
        return new AbstractBalancedBinarySearchTree[] {
            new AvlTree<Integer, Integer>()
        };
    }
    
    @Theory
    @ManyTimes
    public void testTree(AbstractBalancedBinarySearchTree<Integer, Integer, ?> tree) {
        Set<Integer> nums = new HashSet<Integer>();
        int count = 0;
        for (int x: RandomUtil.randomOrder(100)) {
            assertEquals(count, tree.size());
            
            nums.add(x);
            tree.insert(x, x);
            
            count++;
            assertEquals(count, tree.size());
        }
    }
}
