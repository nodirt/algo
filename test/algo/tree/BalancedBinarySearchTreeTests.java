package algo.tree;

import java.util.*;

import org.junit.experimental.theories.*;

import algo.MapTests;

public class BalancedBinarySearchTreeTests extends MapTests {
    @SuppressWarnings({"unchecked"})
    @DataPoints
    public static Map<Integer, Integer>[] trees() {
        return new Map[] {
            new AvlTree<Integer, Integer>()
        };
    }
}
