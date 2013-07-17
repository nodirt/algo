package algo.tree.impl;

import java.util.*;

import algo.tree.*;

public class AbstractBinaryTree<V, N extends AbstractBinaryTree.Node<V, N>>
        extends AbstractRootedTree<V, N> implements BinaryTree<V> {

    public static class Node<V, N extends Node<V, N>> extends AbstractRootedTree.Node<V, N>
            implements
                BinaryTree.Node<V> {
        public N left;
        public N right;

        public BinaryTree.Node<V> getLeft() {
            return left;
        }

        public BinaryTree.Node<V> getRight() {
            return right;
        }

        public N getChild(int direction) {
            return direction < 0 ? left : right;
        }

        public void setChild(int direction, N child) {
            if (direction < 0) {
                left = child;
            } else {
                right = child;
            }
        }

        public boolean hasChild(int direction) {
            return getChild(direction) != null;
        }

        @Override
        public final int childCount() {
            int count = 0;
            if (left != null) {
                count++;
            }
            if (right != null) {
                count++;
            }

            return count;
        }

        @Override
        public Iterable<algo.tree.Node<V>> children() {
            ArrayList<algo.tree.Node<V>> children = new ArrayList<algo.tree.Node<V>>();
            if (left != null) {
                children.add(left);
            }
            if (right != null) {
                children.add(right);
            }
            return children;
        }
    }

    /* traversal */

    public void inOrder(final Visitor<N> visitor) {
        class Traversal {
            void run(N node) {
                if (node == null) return;
                run(node.left);
                visitor.pre(node);
                run(node.right);
            }
        }

        new Traversal().run(mRoot);
    }

    public void preOrder(final Visitor<N> visitor) {
        class Traversal {
            void run(N node) {
                if (node == null) return;
                visitor.pre(node);
                run(node.left);
                run(node.right);
            }
        }

        new Traversal().run(mRoot);
    }

    public void postOrder(final Visitor<N> visitor) {
        class Traversal {
            void run(N node) {
                if (node == null) return;
                run(node.left);
                run(node.right);
                visitor.pre(node);
            }
        }

        new Traversal().run(mRoot);
    }

    protected N rotate(N node, int direction) {
        if (node == null) {
            throw new IllegalArgumentException("node must not be null");
        }
        N other = node.getChild(-direction);
        if (other == null) {
            throw new IllegalArgumentException("node does not have a child");
        }
        node.setChild(-direction, other.getChild(direction));
        other.setChild(direction, node);
        return other;
    }
}
