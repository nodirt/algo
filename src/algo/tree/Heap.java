package algo.tree;

import java.util.*;

import algo.util.DefaultComparator;

@SuppressWarnings("unchecked")
public class Heap<E> {
    public final Comparator<E> comparator;
    E[] mItems;
    int mSize;

    public Heap(E[] storage, int count, Comparator<E> comparator) {
        if (storage == null) {
            throw new IllegalArgumentException("Storage must not be null");
        }
        if (count > storage.length) {
            throw new IllegalArgumentException("Count cannot exceed capacity");
        }
        if (comparator == null) {
            comparator = new DefaultComparator<E>();
        }

        this.comparator = comparator;
        this.mItems = storage;
        this.mSize = count;

        maxHeapifyAll();
    }

    public Heap(E[] storage, Comparator<E> comparator) {
        this(storage, storage.length, comparator);
    }

    public Heap(E[] storage) {
        this(storage, null);
    }

    public Heap(int capacity, Iterable<E> items, Comparator<E> comparator) {
        this((E[]) new Object[capacity], 0, comparator);

        if (items != null) {
            for (E item : items) {
                mItems[mSize] = item;
                mSize++;
                if (mSize >= capacity) {
                    break;
                }
            }
        }
        maxHeapifyAll();
    }

    public Heap(Collection<E> items, Comparator<E> comparator) {
        this((E[]) items.toArray(), comparator);
    }

    public Heap(Collection<E> items) {
        this(items, null);
    }

    public Heap(int capacity, Comparator<E> comparator) {
        this((E[]) new Object[capacity], comparator);
    }

    public Heap(int capacity) {
        this(capacity, null);
    }

    public int size() {
        return mSize;
    }

    public boolean isEmpty() {
        return mSize == 0;
    }

    public boolean isFull() {
        return mSize == mItems.length;
    }

    int getLeft(int index) {
        return index * 2;
    }

    int getRight(int index) {
        return index * 2 + 1;
    }

    int getParent(int index) {
        return index / 2;
    }

    boolean greater(int i, int j) {
        return comparator.compare(mItems[i], mItems[j]) > 0;
    }

    void swap(int i, int j) {
        E tmp = mItems[i];
        mItems[i] = mItems[j];
        mItems[j] = tmp;
    }

    void maxHeapify(int index) {
        while (true) {
            int left = getLeft(index);
            int right = getRight(index);
            int largest = index;

            if (left < mSize && greater(left, largest)) {
                largest = left;
            }
            if (right < mSize && greater(right, largest)) {
                largest = right;
            }

            if (largest == index) break;

            swap(index, largest);
            index = largest;
        }
    }

    void maxHeapifyAll() {
        for (int i = mSize / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    void bubbleUp(int index) {
        while (index > 0) {
            int parent = getParent(index);
            assert parent < index;
            if (!greater(index, parent)) break;
            swap(index, parent);
            index = parent;
        }
    }


    public void put(E item) {
        if (mSize >= mItems.length) {
            throw new RuntimeException("Heap size is exceeded");
        }

        mItems[mSize] = item;
        mSize++;
        bubbleUp(mSize - 1);
    }

    public E max() {
        if (mSize == 0) {
            throw new RuntimeException("Heap is empty");
        }
        return mItems[0];
    }

    public E extractMax() {
        if (mSize == 0) {
            throw new RuntimeException("Heap is empty");
        }
        E max = mItems[0];

        mSize--;
        mItems[0] = mItems[mSize];
        mItems[mSize] = null;
        maxHeapify(0);

        return max;
    }
}
