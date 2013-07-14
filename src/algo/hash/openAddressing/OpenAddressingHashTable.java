package algo.hash.openAddressing;

import java.util.*;

import algo.hash.AbstractHashTable;

@SuppressWarnings("unchecked")
public class OpenAddressingHashTable<K, V> extends AbstractHashTable<K, V> {
    static final Entry<Object, Object> DELETED = new Entry<Object, Object>(null, 0, null);

    public OpenAddressingHashTable(ProbingStrategy probing, float loadFactor) {
        super(probing, loadFactor);
    }

    public OpenAddressingHashTable(ProbingStrategy probing) {
        this(probing, 0.5f);
    }

    public OpenAddressingHashTable(int capacity) {
        this(new LinearProbing(capacity));
    }

    public OpenAddressingHashTable() {
        this(4);
    }

    @Override
    protected boolean isEntryEmpty(Entry<K, V> entry) {
        return entry == null || entry == DELETED;
    }

    Iterator<Integer> probe(int hashValue) {
        ProbingStrategy probing = (ProbingStrategy) mStrategy;
        return probing.probe(hashValue);
    }

    void put(Entry<K, V> entry) {
        assert mStrategy.getSize() == mEntries.length;
        for (Iterator<Integer> it = probe(entry.hashValue); it.hasNext();) {
            int index = it.next();
            Entry<K, V> current = mEntries[index];
            if (current == null || current == DELETED) {
                mEntries[index] = entry;
                break;
            } else if (entry.keyMatches(current)) {
                current.value = entry.value;
                break;
            }
        }
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        float newFactor = (float) (mCount + 1) / mEntries.length;
        if (newFactor > mLoadFactor) {
            enlarge();
        }

        put(new Entry<K, V>(key, value));
        mCount += 1;
        return value;
    }

    void enlarge() {
        Entry<K, V>[] old = mEntries;
        mEntries = new Entry[mStrategy.increaseSize()];

        for (Entry<K, V> entry : old) {
            if (entry != null && entry != DELETED) {
                put(entry);
            }
        }
    }

    @Override
    protected int search(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        int hashCode = key.hashCode();

        for (Iterator<Integer> it = probe(hashCode); it.hasNext();) {
            int index = it.next();
            Entry<K, V> entry = mEntries[index];
            if (entry == DELETED) continue;
            if (entry == null) break;

            if (entry.hashValue == hashCode && entry.key.equals(key)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public V remove(Object key) {
        int index = search((K) key);
        if (index == -1) {
            return null;
        }
        V value = mEntries[index].value;
        mEntries[index] = (Entry<K, V>) DELETED;
        mCount--;
        return value;
    }

}
