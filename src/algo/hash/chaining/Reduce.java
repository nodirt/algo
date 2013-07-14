package algo.hash.chaining;

import algo.hash.HashTableStrategy;

public abstract class Reduce extends HashTableStrategy {
    public Reduce(int size) {
        super(size);
    }
    
    public abstract int reduce(int key);
}
