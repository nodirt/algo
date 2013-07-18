package algo.util;

import java.util.Iterator;

public abstract class BaseIterator<E> implements Iterator<E> {
    protected E mNext;
    protected boolean mHasNext = false;
    protected boolean mInitialized = false;
    
    protected abstract boolean moveNext();

    void findNext() {
        mHasNext = moveNext();
        if (!mHasNext) {
            mNext = null;
        }
    }
    void init() {
        if (mInitialized) return;
        mInitialized = true;
        findNext();
    }

    @Override
    public boolean hasNext() {
        init();
        return mHasNext;
    }

    @Override
    public E next() {
        init();
        E current = mNext;
        findNext();
        return current;
    }

    @Override
    public final void remove() {
    }
    
}
