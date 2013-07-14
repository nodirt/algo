package algo.hash;

import java.util.*;

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
    
    protected AbstractHashTable(float loadFactor) {
        mLoadFactor = loadFactor;
    }
}
