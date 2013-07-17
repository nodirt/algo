package algo.tree;

public interface BinaryTree<V> extends RootedTree<V> {

    public static interface Node<V> extends algo.tree.Node<V> {
        Node<V> getLeft();
        Node<V> getRight();
    }

    Node<V> getRoot();
}
