package algo.hash.chaining;

import algo.util.*;

public class DivisionMethod extends Reduce {
    
    public DivisionMethod(int size) {
        super(PrimeNumbers.ceiling(size));
    }
    public DivisionMethod() {
        this(11);
    }
    
    
    @Override
    public int increaseSize() {
        mSize = PrimeNumbers.ceiling(mSize * 2);
        return mSize;
    }

    @Override
    public int reduce(int key) {
        return Math.abs(key % size());
    }
    
}
