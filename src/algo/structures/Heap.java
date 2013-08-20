package algo.structures;

import java.util.*;

import static algo.util.Util.*;
import algo.util.*;

@SuppressWarnings("unchecked")
public class Heap<T> implements PriorityQueue<T> {
    public final Comparator<T> comparator;
    Object[] mItems;
    int mSize;
    
    Heap(Object[] elements, int count, Comparator<T> comparator) {
        if (comparator == null) {
            comparator = new DefaultComparator<T>();
        }
        this.comparator = comparator;
        this.mItems = elements;
        mSize = count;
        for (int i = mSize / 2; i >= 0; i--) {
            heapify(i);
        }
    }
    public Heap(int capacity, Comparator<T> comparator) {
        this(new Object[capacity], 0, comparator);
    }
    
    public static <T> Heap<T> from(T[] elements) {
        return new Heap<T>(elements, elements.length, null);
    }
    
    @Override
    public boolean isEmpty() {
        return mSize == 0;
    }
    
    @Override
    public int getSize() {
        return mSize;
    }
    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }
    
    @Override
    public T max() {
        return (T) mItems[0];
    }
    
    @Override
    public T extractMax() {
        T max = this.max();
        mSize--;
        mItems[0] = mItems[mSize];
        mItems[mSize] = null;
        heapify(0);
        return max;
    }
    @Override
    public void add(T element) {
        if (mSize + 1 >= mItems.length) {
            mItems = Arrays.copyOf(mItems, mItems.length * 2);
        }
        mItems[mSize] = element;
        mSize++;
        bubbleUp(mSize - 1);
    }
    
    int left(int index) {
        return index * 2;
    }
    int right(int index) {
        return index * 2 + 1;
    }
    int parent(int index) {
        return index / 2;
    }
    
    boolean isGreater(int i, int j) {
        return comparator.compare((T) mItems[i], (T) mItems[j]) > 0;
    }
    void heapify(int i) {
        while (left(i) < mSize) {
            int largest = left(i);
            if (right(i) < mSize && isGreater(right(i), largest)) {
                largest = right(i);
            }
            
            if (!isGreater(largest, i)) { 
                break;
            }
            swap(mItems, i, largest);
            i = largest;
        }
    }

   
    void bubbleUp(int index) {
        while (index > 0 && isGreater(index, parent(index))) {
            swap(mItems, index, parent(index));
            index = parent(index);
        }
    }

}