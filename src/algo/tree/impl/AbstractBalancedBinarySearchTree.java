package algo.tree.impl;

import algo.tree.*;

public abstract class AbstractBalancedBinarySearchTree<K, V, N extends AbstractBinarySearchTree.Node<K, V, N>>
        extends AbstractBinarySearchTree<K, V, N> implements BalancedTree {

    protected abstract boolean isBalanced(N node);

    protected boolean isBalanced(N node, boolean recursive) {
        if (!isBalanced(node)) return false;

        if (recursive) {
            if (node.left != null && !isBalanced(node.left, true)) return false;
            if (node.right != null && !isBalanced(node.right, true)) return false;
        }

        return true;
    }

    public boolean isBalanced() {
        return isBalanced(getRoot(), true);
    }

    @Override
    public N insertNode(N node) {
        node = super.insertNode(node);
        assert isBalanced();
        return node;
    }

    /* remove */

    public boolean supportsRemove() {
        return true;
    }

    @Override
    public N removeNode(K key) {
        if (!supportsRemove()) {
            throw new UnsupportedOperationException("Remove is not supported");
        }
        @SuppressWarnings("unchecked")
        N node = (N) super.removeNode(key);
        assert isBalanced();
        return node;
    }
}
