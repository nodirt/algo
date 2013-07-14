package algo.hash;

import java.util.*;

@SuppressWarnings("unchecked")
public abstract class AbstractHashTable<K, V> implements Map<K, V>{
    protected static class Entry<K, V> implements Map.Entry<K, V> {
        public K key;
        public int hashValue;
        public V value;
        
        public Entry(K key, int hashValue, V value) {
            this.key = key;
            this.hashValue = hashValue;
            this.value = value;
        }
        
        public Entry(K key, V value) {
            this(key, key.hashCode(), value);
        }
        
        public boolean keyMatches(Entry<K, V> other) {
            return other != null && other.hashValue == hashValue && key.equals(other.key);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }
    }

    protected float mLoadFactor;
    protected Entry<K, V>[] mEntries;
    protected final HashTableStrategy mStrategy;
    protected int mCount;

    protected AbstractHashTable(HashTableStrategy strategy, float loadFactor) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy must not be null");
        }
        mStrategy = strategy;
        mLoadFactor = loadFactor;
        mEntries = new Entry[strategy.getSize()];
    }
    
    public int size() {
        return mCount;
    }
    
    protected abstract int search(K key);
    public V get(K key, V fallbackValue) {
        int index = search(key);
        return index >= 0 ? mEntries[index].value : fallbackValue;
    }
    
    
    @Override
    public V get(Object key) {
        return get((K) key, null);
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

    protected boolean isEntryEmpty(Entry<K, V> entry) {
        return entry == null;
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
