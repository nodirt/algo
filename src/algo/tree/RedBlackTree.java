package algo.tree;

import java.util.Comparator;

import algo.tree.impl.*;

public class RedBlackTree<K, V> extends AbstractRedBlackTree<K, V, RedBlackTree.Node<K, V>> {

    static class Node<K, V> extends AbstractRedBlackTree.Node<K, V, Node<K, V>> {}
    
    public RedBlackTree(Comparator<K> comparator) {
        super(comparator);
    }
    public RedBlackTree() {}

    @Override
    protected Node<K, V> createNode() {
        return new Node<K, V>();
    }
}
