package algo.tree;

import java.util.*;

import org.junit.Assert;
import org.junit.experimental.theories.*;

import algo.MapTests;

public class BalancedBinarySearchTreeTests extends MapTests {
    @SuppressWarnings({"unchecked"})
    @DataPoints
    public static Map<Integer, Integer>[] trees() {
        return new Map[] {new AvlTree<Integer, Integer>(), new RedBlackTree<Integer, Integer>()};
    }

    @Override
    protected boolean shouldTestRemove(Map<Integer, Integer> map) {
        AbstractBalancedBinarySearchTree<?, ?, ?> tree =
                (AbstractBalancedBinarySearchTree<?, ?, ?>) map;
        return tree.supportRemove();
    }

    @Override
    protected void assertMapsEqual(Map<Integer, Integer> expected, Map<Integer, Integer> actual) {
        // TODO Auto-generated method stub
        super.assertMapsEqual(expected, actual);

        if (actual instanceof BalancedTree) {
            BalancedTree tree = (BalancedTree) actual;
            Assert.assertTrue(tree.isBalanced());
        }
    }
}
