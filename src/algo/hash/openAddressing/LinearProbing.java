package algo.hash.openAddressing;

import java.util.*;

public class LinearProbing extends ProbingStrategy {
    
    public LinearProbing(int size) {
        super(size);
    }
    public LinearProbing() {
    }
    
    @Override
    public Iterator<Integer> probe(final int key) {
        return new Iterator<Integer>() {
            int mIndex = modSize(key);

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
            	int result = mIndex;
            	mIndex = modSize(mIndex + 1);
            	return result;
            }

            @Override
            public void remove() {}
        };
    }    
}
