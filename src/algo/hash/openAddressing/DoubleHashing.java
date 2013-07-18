package algo.hash.openAddressing;

import java.util.Iterator;

import algo.util.*;

public class DoubleHashing extends ProbingStrategy {

    private Function<Integer, Integer> mFunc2;

    public DoubleHashing(Function<Integer, Integer> fn2) {
        if (fn2 == null) {
            throw new IllegalArgumentException("Function2 must not be null");
        }
        mFunc2 = fn2;
    }

    public DoubleHashing() {
        this(new Function<Integer, Integer>() {
            public Integer apply(Integer key) {
                if (key % 2 == 0) {
                    key--;
                }
                return Math.abs(key);
            }
        });
    }

    @Override
    public Iterator<Integer> probe(final int key) {
        return new BaseIterator<Integer>() {
            int key2 = mFunc2.apply(key);
            int mIndex = modSize(key);
            
            @Override
            protected boolean moveNext() {
                mNext = mIndex;
                mIndex = modSize(mIndex + key2);
                return true;
            }
        };
    }
}
