package algo.tree;

import java.util.*;

public class Trees {

    private Trees() {

    }

    // Traversal

    public static <V> void inOrder(BinaryTree<V> tree, final Visitor<BinaryTree.Node<V>> visitor) {
        class Traversal {
            void run(BinaryTree.Node<V> node) {
                if (node == null) return;
                run(node.getLeft());
                visitor.pre(node);
                run(node.getRight());
            }
        }

        new Traversal().run((BinaryTree.Node<V>) tree.getRoot());
    }

    public static <V> void preOrder(BinaryTree<V> tree, final Visitor<BinaryTree.Node<V>> visitor) {
        class Traversal {
            void run(BinaryTree.Node<V> node) {
                if (node == null) return;
                visitor.pre(node);
                run(node.getLeft());
                run(node.getRight());
            }
        }

        new Traversal().run((BinaryTree.Node<V>) tree.getRoot());
    }

    public static <V> void postOrder(BinaryTree<V> tree, final Visitor<BinaryTree.Node<V>> visitor) {
        class Traversal {
            void run(BinaryTree.Node<V> node) {
                if (node == null) return;
                run(node.getLeft());
                run(node.getRight());
                visitor.pre(node);
            }
        }

        new Traversal().run((BinaryTree.Node<V>) tree.getRoot());
    }

    // Search

    @SuppressWarnings("unchecked")
    public static <K, V> BinarySearchTree.Node<K, V> find(BinarySearchTree<K, V> tree, K key) {
        BinarySearchTree.Node<K, V> node = (BinarySearchTree.Node<K, V>) tree.getRoot();
        Comparator<K> comparator = tree.getComparator();
        while (node != null) {
            int direction = comparator.compare(key, node.getKey());
            if (direction == 0) {
                break;
            }

            node = (BinarySearchTree.Node<K, V>) (direction < 0 ? node.getLeft() : node.getRight());
        }

        return node;
    }
}
