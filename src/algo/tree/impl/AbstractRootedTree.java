package algo.tree.impl;

import java.util.*;

import algo.tree.*;
import algo.util.Util;

public abstract class AbstractRootedTree<V, N extends AbstractRootedTree.Node<V, N>>
        implements
            RootedTree<V> {

    public static abstract class Node<V, N> implements algo.tree.Node<V> {
        public V value;

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }

    protected N mRoot;
    protected int mSize;

    public N getRoot() {
        return mRoot;
    }

    public int size() {
        return mSize;
    }

    public void clear() {
        mRoot = null;
        mSize = 0;
    }

    public void dfs(final Visitor<N> visitor) {
        class Dfs {
            @SuppressWarnings("unchecked")
            boolean run(N node) {
                if (node == null) return true;
                visitor.pre(node);
                if (visitor.isInterrupted()) return false;

                for (algo.tree.Node<V> child : node.children()) {
                    if (!run((N) child)) return false;
                }

                visitor.post(node);
                return !visitor.isInterrupted();
            }
        }

        new Dfs().run(mRoot);
    }

    public Collection<V> values() {
        final List<V> values = new ArrayList<V>();

        dfs(new Visitor<N>() {
            public void pre(N node) {
                values.add(node.value);
            }
        });

        return values;
    }

    public boolean containsValue(final Object value) {
        class ContainsValue extends Visitor<N> {
            public boolean found = false;

            public void pre(N node) {
                if (Util.equal(value, node.value)) {
                    found = true;
                    interrupt();
                }
            }
        }

        ContainsValue visitor = new ContainsValue();
        dfs(visitor);
        return visitor.found;
    }

    public boolean isEmpty() {
        return mRoot == null;
    }
}
