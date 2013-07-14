package algo.hash;

public abstract class AbstractHashTable<K, V> {
    protected static class Entry<K, V> {
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
    }

    protected float mLoadFactor;
    
    protected AbstractHashTable(float loadFactor) {
        mLoadFactor = loadFactor;
    }
    
    public abstract int size();
    public abstract void put(K key, V value);
    public abstract V get(K key);
    public abstract V remove(K key);
}
