package algo.tree;

public class RedBlackTree<K, V> extends AbstractBalancedBinarySearchTree<K, V, RedBlackTree.Node<K, V>> {

    static class Node<K, V> extends AbstractBinarySearchTree.Node<K, V, Node<K, V>> {
        boolean isRed;
    }
    
    boolean isRed(Node<K, V> node) {
        return node != null && node.isRed;
    }
    
    @Override
    protected boolean isBalanced(Node<K, V> node) {
        if (isRed(node)) {
            return !isRed(node.left) && !isRed(node.right);
        } else {
            return true;
        }
    }
}
