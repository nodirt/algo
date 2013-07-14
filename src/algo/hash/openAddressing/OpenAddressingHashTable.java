package algo.hash.openAddressing;

import java.util.Iterator;

import algo.hash.AbstractHashTable;

public class OpenAddressingHashTable<K, V> extends AbstractHashTable<K, V> {
    static final Entry<Object, Object> DELETED = new Entry<Object, Object>(null, 0, null);
    
    Entry<K, V>[] mEntries;
    ProbingStrategy mProbing;
    int mCount;
    
    @SuppressWarnings("unchecked")
    public OpenAddressingHashTable(ProbingStrategy probing, float loadFactor) {
        super(loadFactor);
        mProbing = probing;
        mEntries = new Entry[mProbing.getSize()];
    }
    public OpenAddressingHashTable(int capacity) {
        this(new LinearProbing(capacity), 0.5f);
    }
    public OpenAddressingHashTable() {
        this(4);
    }

    public int size() {
        return mCount;
    }

    void put(Entry<K, V> entry) {
        assert mProbing.getSize() == mEntries.length;
        for (Iterator<Integer> it = mProbing.probe(entry.hashValue); it.hasNext(); ) {
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
    
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        float newFactor = (float) (mCount + 1) / mEntries.length;
        if (newFactor > mLoadFactor) {
            enlarge();
        }
        
        put(new Entry<K, V>(key, value));
        mCount += 1;
    }
    
    @SuppressWarnings("unchecked")
    void enlarge() {
        Entry<K, V>[] old = mEntries;
        mProbing.increaseSize();
        mEntries = new Entry[mProbing.getSize()];
        
        for(Entry<K, V> entry: old) {
            if (entry != null && entry != DELETED) {
                put(entry);
            }
        }
    }
    
    int search(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        int hashCode = key.hashCode();
        
        for (Iterator<Integer> it = mProbing.probe(hashCode); it.hasNext(); ) {
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
    
    public V get(K key, V fallbackValue) {
        int index = search(key);
        return index >= 0 ? mEntries[index].value : fallbackValue;
    }
    public V get(K key) {
        return get(key, null);
    }
    
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        int index = search(key);
        if (index == -1) {
            return null;
        }
        V value = mEntries[index].value;
        mEntries[index] = (Entry<K, V>) DELETED;
        mCount--;
        return value;
    }
}
