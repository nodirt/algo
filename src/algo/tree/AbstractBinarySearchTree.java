package algo.tree;

import java.util.*;

import algo.util.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class AbstractBinarySearchTree<K, V, N extends AbstractBinarySearchTree.Node<K, V, N>> extends AbstractBinaryTree<V, N> {
    public final Comparator<K> comparator;
    
    static class Node<K, V, N extends AbstractBinarySearchTree.Node<K, V, N>> extends AbstractBinaryTree.Node<V, N>{
        public K key;
    }
    
    public AbstractBinarySearchTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }
    public AbstractBinarySearchTree() {
        this(new DefaultComparator<K>());
    }
    
    public N find(K key) {
        N node = getRoot();
        while (node != null) {
            int direction = comparator.compare(key, node.key);
            if (direction == 0) break;
            
            node = node.getChild(direction);
        }
        
        return node;
    }
    
    protected N insert(N root, N newNode) {
        assert newNode != null;
        if (root == null) {
            return newNode;
        }
        
        int direction = comparator.compare(newNode.key, root.key);
        N child = root.getChild(direction);
        child = insert(root, newNode);
        root.setChild(direction, child);
        return root;
    }
    
    public N insert(N node) {
        mRoot = insert(mRoot, node);
        return node;
    }
    
    protected N createNode() {
        return (N) new Node();
    }
    public N createNode(K key, V value) {
        N node = createNode();
        node.key = key;
        node.value = value;
        return node;
    }
    public N insert(K key, V value) {
        return insert(createNode(key, value));
    }
    
    protected N remove(N root, K key) {
        if (root == null) {
            return null;
        }
        
        int direction = comparator.compare(key, root.key);
        if (direction != 0) {
            N child = root.getChild(direction);
            child = remove(root, key);
            root.setChild(direction, child);
            return root;
        }
        
        // we have found the node to remove
        
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        // replace with the successor
        
        N parentOfSuccesor = root.right;
        N successor;
        do {
            successor = root.left;
        } while (successor.left != null);
        
        assert successor.left == null;
        parentOfSuccesor.left = successor.right;
        successor.left = root.left;
        successor.right = root.right;
        return successor;
    }
    public N remove(K key) {
        return remove(mRoot, key);
    }
}
