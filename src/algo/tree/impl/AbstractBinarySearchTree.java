package algo.tree.impl;

import java.util.*;

import algo.tree.*;
import algo.util.*;

@SuppressWarnings({"unchecked"})
public abstract class AbstractBinarySearchTree<K, V, N extends AbstractBinarySearchTree.Node<K, V, N>>
        extends AbstractBinaryTree<V, N> implements BinarySearchTree<K, V>, Map<K, V> {
    public final Comparator<K> comparator;

    public static class Node<K, V, N extends AbstractBinarySearchTree.Node<K, V, N>>
            extends AbstractBinaryTree.Node<V, N>
            implements
                BinarySearchTree.Node<K, V>,
                Map.Entry<K, V> {
        public K key;

        @Override
        public K getKey() {
            return key;
        }
    }

    protected AbstractBinarySearchTree(Comparator<K> comparator) {
        if (comparator == null) {
            comparator = createDefaultComparator();
        }
        this.comparator = comparator;
    }

    protected AbstractBinarySearchTree() {
        this(null);
    }

    protected Comparator<K> createDefaultComparator() {
        return new DefaultComparator<K>();
    }

    @Override
    public Comparator<K> getComparator() {
        return comparator;
    }

    /* insertion */

    protected abstract N createNode();

    public N createNode(K key, V value) {
        N node = createNode();
        node.key = key;
        node.value = value;
        return node;
    }

    protected N fixInsertion(N node, int direction) {
        return node;
    }

    protected N insertNode(N root, N newNode) {
        assert newNode != null;
        if (root == null) {
            return newNode;
        }

        int direction = comparator.compare(newNode.key, root.key);
        N child = root.getChild(direction);
        child = insertNode(child, newNode);
        root.setChild(direction, child);
        return fixInsertion(root, direction);
    }

    public N insertNode(N node) {
        mRoot = insertNode(mRoot, node);
        mSize++;
        return node;
    }

    public N insertNode(K key, V value) {
        return insertNode(createNode(key, value));
    }

    @Override
    public BinarySearchTree.Node<K, V> insert(K key, V value) {
        return insertNode(key, value);
    }

    @Override
    public V put(K key, V value) {
        return insertNode(key, value).value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    /* remove */

    protected N fixRemove(N node) {
        return node;
    }

    private N removeNodeImpl(N root, K key, Visitor<N> visitor) {
        if (root == null) {
            return null;
        }

        int direction = comparator.compare(key, root.key);
        if (direction != 0) {
            N child = root.getChild(direction);
            child = removeNode(child, key, visitor);
            root.setChild(direction, child);
            return root;
        }

        // we have found the node to remove
        if (visitor != null) {
            visitor.pre(root);
        }

        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        // replace with the successor

        N successor = root.right;
        Stack<N> path = new Stack<N>();
        do {
            path.push(successor);
            successor = successor.left;
        } while (successor.left != null);

        assert successor.left == null; // no elements smaller than successor
        assert path.peek().left == successor; // the top of the stack is successor's parent

        path.peek().left = successor.right; // update successor's parent
        while (path.size() > 1) {
            N child = fixRemove(path.pop());
            path.peek().left = child;
        }

        successor.left = root.left;
        successor.right = fixRemove(path.pop());
        return successor;
    }

    protected N removeNode(N root, K key, Visitor<N> visitor) {
        root = removeNodeImpl(root, key, visitor);
        if (root != null) {
            root = fixRemove(root);
        }
        return root;
    }
    
    @Override
    public boolean supportsRemove() {
        return false;
    }

    public BinarySearchTree.Node<K, V> removeNode(K key) {
        class Remover extends Visitor<N> {
            N removed;

            public void pre(N node) {
                removed = node;
            }
        }

        Remover visitor = new Remover();
        mRoot = removeNode(mRoot, key, visitor);

        if (visitor.removed != null) {
            mSize--;
            assert mSize >= 0;
        }
        return visitor.removed;
    }

    public V remove(Object key) {
        N node = (N) removeNode((K) key);
        return node != null ? node.value : null;
    }

    /* search */

    public N find(K key) {
        N node = getRoot();
        while (node != null) {
            int direction = comparator.compare(key, node.key);
            if (direction == 0) break;

            node = node.getChild(direction);
        }

        return node;
    }

    @Override
    public boolean containsKey(Object key) {
        return find((K) key) != null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        final Set<Map.Entry<K, V>> entries = new HashSet<Map.Entry<K, V>>();

        dfs(new Visitor<N>() {
            public void pre(N node) {
                entries.add(node);
            }
        });

        return entries;
    }

    @Override
    public V get(Object key) {
        N node = find((K) key);
        return node != null ? node.value : null;
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();

        dfs(new Visitor<N>() {
            public void pre(N node) {
                keys.add(node.key);
            }
        });

        return keys;
    }

}
