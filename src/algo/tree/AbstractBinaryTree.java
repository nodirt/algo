package algo.tree;

public class AbstractBinaryTree<V, N extends AbstractBinaryTree.Node<V, N>> extends AbstractRootedTree<V, N> {
    
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
        public final N childAt(int index) {
            if (index == 0) {
                return left != null ? left : right;
            } else {
                return right;
            }
        }
    }

    N rotate(N node, int direction) {
        N other = node.getChild(-direction);
        node.setChild(-direction, other.getChild(direction));
        other.setChild(direction, node);
        return other;
    }
}
