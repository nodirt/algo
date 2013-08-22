package algo.structures;

public class IntegerDisjointSet {
    
    public static class Disjoint {
        int rank;
        int parent;
        
        public Disjoint(int parent) {
            this.parent = parent;
        }
    }
    
    final Disjoint[] mDisjoints;
    
    public IntegerDisjointSet(int size) {
        mDisjoints = new Disjoint[size];
        
        for (int i = 0; i < mDisjoints.length; i++) {
            mDisjoints[i] = new Disjoint(i);
        }
    }
    
    public int size() {
        return mDisjoints.length;
    }
    
    public int findSet(int member) {
        Disjoint disjoint = mDisjoints[member];
        if (disjoint.parent != member) {
            disjoint.parent = findSet(disjoint.parent);
        }
        return disjoint.parent;
    }
    
    public boolean inSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }
    
    public void unit(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        
        Disjoint aDisjoint = mDisjoints[a];
        Disjoint bDisjoint = mDisjoints[b];
        
        if (aDisjoint.rank > bDisjoint.rank) {
            bDisjoint.parent = a;
        } else {
            aDisjoint.parent = b;
            if (aDisjoint.rank == bDisjoint.rank) {
                bDisjoint.rank++;
            }
        }
    }
}
