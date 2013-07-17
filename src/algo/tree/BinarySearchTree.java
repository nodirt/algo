package algo.tree;

import java.util.*;

public interface BinarySearchTree<K, V> extends BinaryTree<V>, Map<K, V> {

    public interface Node<K, V> extends BinaryTree.Node<V>, Map.Entry<K, V> {
    }

    Comparator<K> getComparator();

    boolean supportsRemove();
    Node<K, V> removeNode(K key);
    Node<K, V> insert(K key, V value);
}
