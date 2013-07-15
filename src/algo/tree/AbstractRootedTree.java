package algo.tree;

public abstract class AbstractRootedTree<V, N extends AbstractRootedTree.Node<V, N>> {
    public static abstract class Node<V, N> {
        public V value;
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
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
}
