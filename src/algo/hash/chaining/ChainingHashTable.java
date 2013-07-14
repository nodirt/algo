package algo.hash.chaining;

import algo.hash.AbstractHashTable;

public class ChainingHashTable<K, V> extends AbstractHashTable<K, V>{
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    public ChainingHashTable(Reduce reduce, float loadFactor) {
        super(reduce, loadFactor);
    }
    public ChainingHashTable(Reduce reduce) {
        this(reduce, DEFAULT_LOAD_FACTOR);
    }
    public ChainingHashTable(int capacity) {
        this(new DivisionMethod(capacity));
    }
    public ChainingHashTable() {
        this(new DivisionMethod());
    }
    
    int reduce(int hashValue) {
        Reduce reduce = (Reduce) mStrategy;
        return reduce.reduce(hashValue);
    }
    
    @Override
    protected void put(Entry<K, V> entry) {
        int index = reduce(entry.hashValue);
        assert index >= 0 && index < mEntries.length;
        entry.next = mEntries[index];
        mEntries[index] = entry;
    }
    
    @Override
    protected void putDuringEnlarge(algo.hash.AbstractHashTable.Entry<K, V> entry) {
        super.putDuringEnlarge(new Entry<K, V>(entry.key, entry.hashValue, entry.value));
    }
    
    @Override
    public V remove(Object key) {
        int hashValue = key.hashCode();
        int index = reduce(hashValue);
        assert index >= 0 && index < mEntries.length;
        
        @SuppressWarnings("unchecked")
        K kkey = (K) key;
        V value;
        
        Entry<K, V> entry = mEntries[index];
        if (entry.keyMatches(hashValue, kkey)) {
            mEntries[index] = entry.next;
            value = entry.value;
        } else {
            while (entry.next != null && !entry.next.keyMatches(hashValue, kkey)) {
                entry = entry.next;
            }
            if (entry.next == null) {
                return null;
            }
            
            value = entry.next.value;
            entry.next = entry.next.next;
        }

        mCount--;
        return value;
    }
    @Override
    protected Entry<K, V> find(K key) {
        int hashValue = key.hashCode();
        int index = reduce(hashValue);
        assert index >= 0 && index < mEntries.length;
        
        Entry<K, V> entry = mEntries[index];
        while (entry != null && !entry.keyMatches(hashValue, key)) {
            entry = entry.next;
        }
        
        return entry;
    }
    
}
