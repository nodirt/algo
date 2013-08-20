package algo;

import java.util.*;

public class DisjointSet<T> {
    
    public static class Disjoint<T> {
        int rank;
        Disjoint<T> parent;
        
        public Disjoint() {
            parent = this;
        }
    }
    
    final Map<T, Disjoint<T>> mNodes;
    
    public DisjointSet() {
        mNodes = new HashMap<T, Disjoint<T>>();
    }
    
    public Disjoint<T> makeSet(T element) {
        Disjoint<T> disjoint = new Disjoint<T>();
        mNodes.put(element, disjoint);
        return disjoint;
    }
    
    public int size() {
        return mNodes.size();
    }
    
    Disjoint<T> findSet(Disjoint<T> disjoint) {
        if (disjoint == null) {
            return null;
        }
        
        if (disjoint.parent != disjoint) {
            disjoint.parent = findSet(disjoint.parent);
        }
        return disjoint.parent;
    }
    public Disjoint<T> findSet(T member) {
        return findSet(mNodes.get(member));
    }
    
    public boolean inSameSet(T a, T b) {
        return findSet(a) == findSet(b);
    }
    
    public void unit(T a, T b) {
        Disjoint<T> aDisjoint = findSet(a);
        Disjoint<T> bDisjoint = findSet(b);
        
        if (aDisjoint.rank > bDisjoint.rank) {
            bDisjoint.parent = aDisjoint;
        } else {
            aDisjoint.parent = bDisjoint;
            if (aDisjoint.rank == bDisjoint.rank) {
                bDisjoint.rank++;
            }
        }
    }
}
