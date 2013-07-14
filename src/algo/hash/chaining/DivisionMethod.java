package algo.hash.chaining;

import algo.util.*;

public class DivisionMethod extends Reduce {
    
    public DivisionMethod(int max) {
        super(max);
    }
    public DivisionMethod() {
        this(11);
    }
    
    @Override
    public int setSize(int value) {
        return super.setSize(PrimeNumbers.ceiling(value));
    }

    @Override
    public int reduce(int key) {
        return Math.abs(key % getSize());
    }
    
}
