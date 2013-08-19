package algo;

public class DisjointSet {
    
    static class Node {
        int rank;
        int parent;
        
        public Node(int parent) {
            this.parent = parent;
        }
    }
    
    final Node[] mNodes;
    
    public DisjointSet(int size) {
        mNodes = new Node[size];
        for (int i = 0; i < size; i++) {
            mNodes[i] = new Node(i);
        }
    }
    
    public int size() {
        return mNodes.length;
    }
    
    public int findSet(int member) {
        Node node = mNodes[member];
        if (node.parent != member) {
            node.parent = findSet(node.parent);
        }
        return node.parent;
    }
    
    public boolean inSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }
    
    public void unit(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        
        Node aNode = mNodes[a];
        Node bNode = mNodes[b];
        
        if (aNode.rank > bNode.rank) {
            bNode.parent = a;
        } else {
            aNode.parent = b;
            if (aNode.rank == bNode.rank) {
                aNode.rank++;
            }
        }
    }
}
