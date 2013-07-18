package algo.hash;

import java.util.*;

import algo.util.*;

@SuppressWarnings("unchecked")
public abstract class AbstractHashTable<K, V> implements Map<K, V> {
    protected static class Entry<K, V> implements Map.Entry<K, V> {
        public K key;
        public int hashValue;
        public V value;
        /** not used in open addressing */
        public Entry<K, V> next;

        public Entry(K key, int hashValue, V value) {
            this.key = key;
            this.hashValue = hashValue;
            this.value = value;
        }

        public Entry(K key, V value) {
            this(key, key.hashCode(), value);
        }

        public boolean keyMatches(int hashValue, K key) {
            return hashValue == this.hashValue && this.key.equals(key);
        }

        public boolean keyMatches(Entry<K, V> other) {
            return other != null && keyMatches(other.hashValue, other.key);
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
        mEntries = new Entry[strategy.size()];
    }

    public int size() {
        return mCount;
    }

    protected abstract void put(Entry<K, V> entry);

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

    protected void putDuringEnlarge(Entry<K, V> entry) {
        put(entry);
    }

    void enlarge() {
        Entry<K, V>[] old = mEntries;
        mEntries = new Entry[mStrategy.increaseSize()];

        for (Entry<K, V> entry : old) {
            if (isEntryEmpty(entry)) continue;
            for (Entry<K, V> e = entry; e != null; e = e.next) {
                putDuringEnlarge(e);
            }
        }
    }

    protected abstract Entry<K, V> find(K key);

    public V get(K key, V fallbackValue) {
        Entry<K, V> entry = find(key);
        return entry != null ? entry.value : fallbackValue;
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
        return find((K) key) != null;
    }

    protected boolean isEntryEmpty(Entry<K, V> entry) {
        return entry == null;
    }

    protected class EntryIterator extends BaseIterator<Entry<K, V>> {
        protected int mIndex = -1;

        @Override
        protected boolean moveNext() {
            if (mNext != null && mNext.next != null) {
                mNext = mNext.next;
                return true;
            }

            do {
                mIndex++;
                if (mIndex >= mEntries.length) {
                    return false;
                }
            } while (isEntryEmpty(mEntries[mIndex]));

            mNext = mEntries[mIndex];
            return true;
        }
    }

    private Iterable<Entry<K, V>> nonEmptyEntries() {
        return new Iterable<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new EntryIterator();
            }
        };
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> e : nonEmptyEntries()) {
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

        for (Entry<K, V> e : nonEmptyEntries()) {
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

        for (Entry<K, V> e : nonEmptyEntries()) {
            keys.add(e.key);
        }

        return keys;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<V>();
        for (Entry<K, V> e : nonEmptyEntries()) {
            values.add(e.value);
        }
        return values;
    }
}
