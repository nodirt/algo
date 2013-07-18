package algo.tree.impl;

import java.util.Comparator;

public abstract class AbstractRedBlackTree<K, V, N extends AbstractRedBlackTree.Node<K, V, N>>
        extends AbstractBalancedBinarySearchTree<K, V, N> {

    public static class Node<K, V, N extends Node<K, V, N>>
            extends AbstractBinarySearchTree.Node<K, V, N> {
        boolean isRed;
    }

    protected AbstractRedBlackTree(Comparator<K> comparator) {
        super(comparator);
    }

    protected AbstractRedBlackTree() {}

    boolean isRed(N node) {
        return node != null && node.isRed;
    }

    boolean isBlack(N node) {
        return node == null || !node.isRed;
    }

    @Override
    protected boolean isBalanced(N node) {
        return isBlack(node) || (isBlack(node.left) && isBlack(node.right));
    }

    @Override
    protected N fixInsertion(N root, int direction) {
        N changed = root.getChild(direction);
        N unchanged = root.getChild(-direction);

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
    public N insertNode(N node) {
        node.isRed = true;
        N result = super.insertNode(node);
        getRoot().isRed = false;
        return result;
    }

    @Override
    public boolean supportsRemove() {
        return false;
    }
}
