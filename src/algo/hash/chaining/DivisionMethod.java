package algo.hash.chaining;

import algo.util.*;

public class DivisionMethod extends Reduce {
    
    public DivisionMethod(int size) {
        super(NumberTheory.ceilingPrime(size));
    }
    public DivisionMethod() {
        this(11);
    }
    
    
    @Override
    public int increaseSize() {
        mSize = NumberTheory.ceilingPrime(mSize * 2);
        return mSize;
    }

    @Override
    public int reduce(int key) {
        return Math.abs(key % size());
    }
    
}
