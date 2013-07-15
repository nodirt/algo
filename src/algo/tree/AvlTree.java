package algo.tree;

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
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
    void updateHeight(Node<K, V> node, boolean recursive) {
        if (node.left != null) {
            updateHeight(node.left, true);
        }
        if (node.right != null) {
            updateHeight(node.right, true);
        }
        
        updateHeight(node);
    }

    @Override
    protected boolean isBalanced(Node<K, V> node) {
        return Math.abs(getHeight(node.left) - getHeight(node.right)) < 2;
    }
    
    Node<K, V> fixBalance(Node<K, V> node) {
        updateHeight(node);
        if (isBalanced(node)) {
            return node;
        }
        
        // if left has greater height, then rotate right. Otherwise, left
        int direction = getHeight(node.left) - getHeight(node.right);
        
        Node<K, V> other = node.getChild(-direction);
        if (!other.hasChild(-direction)) {
            other = rotate(other, -direction);
            node.setChild(-direction, other);
        }
        return rotate(node, direction);
    }

    @Override
    protected Node<K, V> insert(Node<K, V> root, Node<K, V> newNode) {
        return this.fixBalance(super.insert(root, newNode));
    }
    

    @Override
    protected Node<K, V> remove(Node<K, V> root, K key) {
        return fixBalance(super.remove(root, key));
    }
}
