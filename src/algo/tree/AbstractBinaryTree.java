package algo.tree;

import java.util.ArrayList;

public class AbstractBinaryTree<V, N extends AbstractBinaryTree.Node<V, N>>
        extends AbstractRootedTree<V, N> {

    public static class Node<V, N extends Node<V, N>> extends AbstractRootedTree.Node<V, N> {
        public N left;
        public N right;

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
        public Iterable<N> children() {
            ArrayList<N> children = new ArrayList<N>();
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

        new Traversal().run(getRoot());
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

        new Traversal().run(getRoot());
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

        new Traversal().run(getRoot());
    }

    N rotate(N node, int direction) {
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
