package algo.hash.openAddressing;

import java.util.*;

public abstract class ProbingStrategy {
    int mSize;
    
    public ProbingStrategy(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        setSize(size);
    }
    public ProbingStrategy() {
        this(11);
    }
    
    public abstract Iterator<Integer> probe(int key);
    
    public int getSize() {
        return mSize;
    }
    public int setSize(int value) {
        mSize = value;
        return mSize;
    }
    public int increaseSize() {
        mSize *= 2;
        return mSize;
    }
    
    protected int modSize(int key) {
    	return Math.abs(key % mSize);
    }
}
