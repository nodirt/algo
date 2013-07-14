package algo.hash;

import java.util.*;
import java.util.Map.Entry;

import org.junit.Test;

import static org.junit.Assert.*;
import algo.*;

public abstract class HashTableTest extends BaseTestClass {
    
    protected abstract AbstractHashTable<Integer, Integer> create();
    
    void assertTablesEqual(Map<Integer, Integer> expected, AbstractHashTable<Integer, Integer> actual) {
        assertEquals(expected.size(), actual.size());
        
        for (Entry<Integer, Integer> entry: expected.entrySet()) {
            assertEquals(entry.getValue(), actual.get(entry.getKey()));
        }
    }
    
    @Test
    @ManyTimes
    public void putAndGet() {
        AbstractHashTable<Integer, Integer> table = create();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int key = rand.nextInt();
            map.put(key, key);
            table.put(key, key);
            
            assertTablesEqual(map, table);
            
            if (rand.nextBoolean() && i > 0) {
                Object[] keys = map.keySet().toArray();
                int keyToRemove = (Integer) keys[rand.nextInt(keys.length)];
                assertEquals(map.remove(keyToRemove), table.remove(keyToRemove));
                assertTablesEqual(map, table);
            }
        }
    }
}
