package algo.tree;

public interface Node<V> {
    V getValue();
    V setValue(V value);
    
    int childCount();
    Iterable<Node<V>> children();
}
