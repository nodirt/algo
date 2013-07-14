package algo.hash.openAddressing;

import java.util.*;

import algo.hash.HashTableStrategy;

public abstract class ProbingStrategy extends HashTableStrategy {
    protected ProbingStrategy(int size) {
        super(size);
    }
    protected ProbingStrategy() {
        this(4);
    }
    
    public abstract Iterator<Integer> probe(int key);
}
