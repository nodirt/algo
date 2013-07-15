package algo.hash;

import static algo.util.Util.assertMapsEqual;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;

import algo.BaseTestClass;
import algo.ManyTimes;
import algo.hash.chaining.ChainingHashTable;
import algo.hash.chaining.DivisionMethod;
import algo.hash.chaining.MultiplcationMethod;
import algo.hash.chaining.UniversalMethod;
import algo.hash.openAddressing.DoubleHashing;
import algo.hash.openAddressing.LinearProbing;
import algo.hash.openAddressing.OpenAddressingHashTable;

public class HashTableTests extends BaseTestClass {
    
    @SuppressWarnings("unchecked")
    @DataPoints
    public static AbstractHashTable<Integer, Integer>[] createTables() {
        return new AbstractHashTable[] {
            new OpenAddressingHashTable<Integer, Integer>(new LinearProbing()),
            new OpenAddressingHashTable<Integer, Integer>(new DoubleHashing()),
            new ChainingHashTable<Integer, Integer>(new DivisionMethod()),
            new ChainingHashTable<Integer, Integer>(new MultiplcationMethod()),
            new ChainingHashTable<Integer, Integer>(new UniversalMethod())
        };
    }

    
    @Theory
    @ManyTimes
    public void putAndGet(AbstractHashTable<Integer, Integer> table) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int key = rand.nextInt();
            map.put(key, key);
            table.put(key, key);

            assertMapsEqual(map, table);
            
            if (rand.nextBoolean() && i > 0) {
                Object[] keys = map.keySet().toArray();
                int keyToRemove = (Integer) keys[rand.nextInt(keys.length)];
                assertEquals(map.remove(keyToRemove), table.remove(keyToRemove));
                assertMapsEqual(map, table);
            }
        }
    }
}
