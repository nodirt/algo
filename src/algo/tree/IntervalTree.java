package algo.tree;

import java.util.*;

import algo.Interval;
import algo.tree.impl.*;
import algo.util.*;

public class IntervalTree<K, V>
        extends AbstractRedBlackTree<Interval<K>, V, IntervalTree.Node<K, V>> {
    static class Node<K, V> extends AbstractRedBlackTree.Node<Interval<K>, V, Node<K, V>> {
        K max;

        public K getStart() {
            return key.getStart();
        }

        public K getEnd() {
            return key.getEnd();
        }
    }
    static class IntervalStartComparator<E> implements Comparator<Interval<E>> {
        final Comparator<E> mBaseComparator;

        public IntervalStartComparator(Comparator<E> baseComparator) {
            mBaseComparator = baseComparator;
        }

        @Override
        public int compare(Interval<E> o1, Interval<E> o2) {
            return mBaseComparator.compare(o1.getStart(), o2.getStart());
        }
    }

    final Comparator<K> mBaseComparator;

    public IntervalTree(Comparator<K> comparator) {
        super(new IntervalStartComparator<K>(comparator));
        if (comparator == null) {
            comparator = new DefaultComparator<K>();
        }
        mBaseComparator = comparator;
    }

    @Override
    protected Comparator<Interval<K>> createDefaultComparator() {
        return new IntervalStartComparator<K>(new DefaultComparator<K>());
    }

    @Override
    protected Node<K, V> createNode() {
        return new Node<K, V>();
    }

    void updateMax(Node<K, V> node) {
        node.max = node.key.getEnd();
        if (node.left != null) {
            node.max = Util.max(node.max, node.left.getEnd(), mBaseComparator);
        }
        if (node.right != null) {
            node.max = Util.max(node.max, node.right.getEnd(), mBaseComparator);
        }
    }



    @Override
    protected Node<K, V> fixInsertion(Node<K, V> root, int direction) {
        root = super.fixInsertion(root, direction);
        updateMax(root);
        return root;
    }

    boolean intersect(Interval<K> a, Interval<K> b) {
        return mBaseComparator.compare(a.getStart(), b.getEnd()) > 0
                || mBaseComparator.compare(b.getStart(), a.getEnd()) > 0;
    }

    protected Node<K, V> findIntersection(Interval<K> interval) {
        Node<K, V> node = mRoot;
        while (node != null) {
            if (intersect(node.key, interval)) {
                break;
            }

            if (mBaseComparator.compare(interval.getStart(), node.max) >= 0) {
                return null;
            }

            if (node.left != null
                    && mBaseComparator.compare(interval.getStart(), node.left.max) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return node;
    }
}
