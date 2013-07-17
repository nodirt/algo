package algo.tree;

import algo.tree.impl.*;

public class RedBlackTree<K, V>
        extends AbstractBalancedBinarySearchTree<K, V, RedBlackTree.Node<K, V>> {

    static class Node<K, V> extends AbstractBinarySearchTree.Node<K, V, Node<K, V>> {
        boolean isRed;
    }

    @Override
    protected Node<K, V> createNode() {
        return new Node<K, V>();
    }

    boolean isRed(Node<K, V> node) {
        return node != null && node.isRed;
    }

    boolean isBlack(Node<K, V> node) {
        return node == null || !node.isRed;
    }

    @Override
    protected boolean isBalanced(Node<K, V> node) {
        return isBlack(node) || (isBlack(node.left) && isBlack(node.right));
    }

    @Override
    protected Node<K, V> fixInsertion(Node<K, V> root, int direction) {
        Node<K, V> changed = root.getChild(direction);
        Node<K, V> unchanged = root.getChild(-direction);

        if (isRed(changed)) {
            if (isRed(unchanged)) {
                // both children are red.
                // Paint both black, and paint itself red
                changed.isRed = false;
                unchanged.isRed = false;
                root.isRed = true;
            } else {
                if (isRed(changed.getChild(-direction))) {
                    changed = rotate(changed, direction);
                    root.setChild(direction, changed);
                }

                // the child is red, but its brother is black
                changed.isRed = false;
                root.isRed = true;
                root = rotate(root, -direction);
            }
        }

        return root;
    }

    @Override
    public Node<K, V> insertNode(Node<K, V> node) {
        node.isRed = true;
        Node<K, V> result = super.insertNode(node);
        getRoot().isRed = false;
        return result;
    }

    @Override
    public boolean supportsRemove() {
        return false;
    }
}
