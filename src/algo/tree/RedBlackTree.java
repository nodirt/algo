package algo.tree;

import algo.tree.impl.*;

public class RedBlackTree<K, V> extends AbstractRedBlackTree<K, V, RedBlackTree.Node<K, V>> {

    static class Node<K, V> extends AbstractRedBlackTree.Node<K, V, Node<K, V>> {}

    @Override
    protected Node<K, V> createNode() {
        return new Node<K, V>();
    }
}
