package algo.tree;

import java.util.*;

public final class AvlTree<K, V> extends AbstractBalancedBinarySearchTree<K, V, AvlTree.Node<K, V>> {

    static final class Node<K, V> extends AbstractBinarySearchTree.Node<K, V, Node<K, V>> {
        int height = 1;
    }

    public Node<K, V> createNode() {
        return new Node<K, V>();
    }

    int getHeight(Node<K, V> node) {
        return node != null ? node.height : 0;
    }

    void updateHeight(Node<K, V> node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    void updateHeight(Node<K, V> node, boolean recursive) {
        if (recursive && node != null) {
            updateHeight(node.left, true);
            updateHeight(node.right, true);
        }

        updateHeight(node);
    }

    @Override
    Node<K, V> rotate(Node<K, V> node, int direction) {
        Node<K, V> other = super.rotate(node, direction);
        assert other.left == node || other.right == node;
        updateHeight(node);
        updateHeight(other);
        return other;
    }

    @Override
    protected boolean isBalanced(Node<K, V> node) {
        return node == null || Math.abs(getHeight(node.left) - getHeight(node.right)) < 2;
    }

    Node<K, V> fixBalance(Node<K, V> node) {
        updateHeight(node);
        if (isBalanced(node)) {
            return node;
        }

        // if left has greater height, then rotate right. Otherwise, left
        int direction = getHeight(node.left) - getHeight(node.right);

        Node<K, V> other = node.getChild(-direction);
        if (getHeight(other.getChild(direction)) > getHeight(other.getChild(-direction))) {
            other = rotate(other, -direction);
            node.setChild(-direction, other);
            updateHeight(node);
        }

        node = rotate(node, direction);
        assert isBalanced(node, true);
        return node;
    }

    @Override
    protected Node<K, V> fixInsertion(Node<K, V> node) {
        return fixBalance(node);
    }

    @Override
    protected Node<K, V> fixRemove(Node<K, V> node) {
        return fixBalance(node);
    }
}
