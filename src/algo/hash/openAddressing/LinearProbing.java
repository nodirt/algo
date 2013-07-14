package algo.hash.openAddressing;

import java.util.*;

public class LinearProbing<K> extends ProbingStrategy<K> {
    
    public LinearProbing(int size) {
        super(size);
    }
    public LinearProbing() {
    }
    
    @Override
    public Iterator<Integer> probe(final int key) {
        return new Iterator<Integer>() {
            int mIndex = key;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                mIndex = Math.abs((mIndex + 1) % getSize());
                return mIndex;
            }

            @Override
            public void remove() {}
        };
    }    
}
