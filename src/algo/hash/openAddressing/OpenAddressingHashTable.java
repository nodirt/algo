package algo.hash.openAddressing;

import java.util.*;

import algo.hash.AbstractHashTable;

@SuppressWarnings("unchecked")
public class OpenAddressingHashTable<K, V> extends AbstractHashTable<K, V> {
    static final Entry<Object, Object> DELETED = new Entry<Object, Object>(null, 0, null);
    
    Entry<K, V>[] mEntries;
    ProbingStrategy mProbing;
    int mCount;
    
    public OpenAddressingHashTable(ProbingStrategy probing, float loadFactor) {
        super(loadFactor);
        mProbing = probing;
        mEntries = new Entry[mProbing.getSize()];
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
    
    boolean isEntryEmpty(Entry<K, V> entry) {
        return entry == null || entry == DELETED;
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
    
    
    @Override
    public V get(Object key) {
        return get((K) key, null);
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
    
    @Override
    public void clear() {
        Arrays.fill(mEntries, null);
        mCount = 0;
    }
    
    @Override
    public boolean containsKey(Object key) {
        return search((K) key) >= 0;
    }
    
    class EntryIterator implements Iterator<Entry<K, V>> {
        int i = -1;
    
        public EntryIterator() {
            findNext();
        }
        
        void findNext() {
            do {
                i++;
            } while (i < mEntries.length && isEntryEmpty(mEntries[i]));
        }
        
        public boolean hasNext() {
            return i < mEntries.length;
        }
        
        public Entry<K, V> next() {
            return mEntries[i];  
        }
        
        public void remove() {}
    }
    
    private Iterable<Entry<K, V>> nonEmptyEntries() {
        return new Iterable<Entry<K,V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new EntryIterator();
            }
        };
    }
    
    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> e: nonEmptyEntries()) {
            if (value == null) {
                if (e.value == null) return true;
            } else {
                if (value.equals(e.value)) return true;
            }
        }
        
        return false;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> keys = new HashSet<Map.Entry<K, V>>();

        for (Entry<K, V> e: nonEmptyEntries()) {
            keys.add(e);
        }

        return keys;
    }
    @Override
    public boolean isEmpty() {
        return mCount == 0;
    }
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();

        for (Entry<K, V> e: nonEmptyEntries()) {
            keys.add(e.key);
        }

        return keys;
        
    }
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e: m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }
    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<V>();
        for (Entry<K, V> e: nonEmptyEntries()) {
            values.add(e.value);
        }
        return values;
    }
}
