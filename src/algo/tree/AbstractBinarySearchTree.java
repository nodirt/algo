package algo.tree;

import java.util.*;

import algo.util.*;

@SuppressWarnings({"unchecked", "rawtypes"})
public class AbstractBinarySearchTree<K, V, N extends AbstractBinarySearchTree.Node<K, V, N>>
        extends AbstractBinaryTree<V, N> implements Map<K, V> {
    public final Comparator<K> comparator;

    static class Node<K, V, N extends AbstractBinarySearchTree.Node<K, V, N>>
            extends AbstractBinaryTree.Node<V, N> implements Map.Entry<K, V> {
        public K key;

        @Override
        public K getKey() {
            return key;
        }
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
        child = insert(child, newNode);
        root.setChild(direction, child);
        return root;
    }

    public N insert(N node) {
        mRoot = insert(mRoot, node);
        mSize++;
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

    protected N remove(N root, K key, Visitor<N> visitor) {
        if (root == null) {
            return null;
        }

        int direction = comparator.compare(key, root.key);
        if (direction != 0) {
            N child = root.getChild(direction);
            child = remove(child, key, visitor);
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

        N parentOfSuccesor;
        N successor = root.right;
        do {
            parentOfSuccesor = successor;
            successor = successor.left;
        } while (successor.left != null);

        assert successor.left == null;
        parentOfSuccesor.left = successor.right;
        successor.left = root.left;
        successor.right = root.right;
        onReplacedWithSuccessor(successor);
        return successor;
    }


    protected void onReplacedWithSuccessor(N successor) {}

    public N removeNode(K key) {
        class Remover extends Visitor<N> {
            N removed;

            public void pre(N node) {
                removed = node;
            }
        }

        Remover visitor = new Remover();
        mRoot = remove(mRoot, key, visitor);

        if (visitor.removed != null) {
            mSize--;
            assert mSize >= 0;
        }
        return visitor.removed;
    }

    public V remove(Object key) {
        N node = removeNode((K) key);
        return node != null ? node.value : null;
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

    @Override
    public V put(K key, V value) {
        return insert(key, value).value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }
}
