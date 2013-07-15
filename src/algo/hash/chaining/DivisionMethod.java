package algo.hash.chaining;

import static algo.util.Util.*;

public class DivisionMethod extends Reduce {
    
    public DivisionMethod(int size) {
        super(ceilingPrime(size));
    }
    public DivisionMethod() {
        this(11);
    }
    
    
    @Override
    public int increaseSize() {
        mSize = ceilingPrime(mSize * 2);
        return mSize;
    }

    @Override
    public int reduce(int key) {
        return Math.abs(key % size());
    }
    
}
