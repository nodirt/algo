package algo.hash.openAddressing;

import java.util.*;

import algo.util.*;

public class LinearProbing extends ProbingStrategy {

    public LinearProbing(int size) {
        super(size);
    }

    public LinearProbing() {}

    @Override
    public Iterator<Integer> probe(final int key) {
        return new BaseIterator<Integer>() {
            int mIndex = modSize(key);

            @Override
            protected boolean moveNext() {
                mNext = mIndex;
                mIndex = modSize(mIndex + 1);
                return true;
            }
        };
    }
}
