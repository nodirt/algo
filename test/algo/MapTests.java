package algo;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.experimental.theories.Theory;

public abstract class MapTests extends BaseTestClass {

    protected void assertMapsEqual(Map<Integer, Integer> expected, Map<Integer, Integer> actual) {
        algo.util.Util.assertMapsEqual(expected, actual);
    }

    @Theory
    @ManyTimes
    public void putAndGet(Map<Integer, Integer> actual) {
        Map<Integer, Integer> expected = new HashMap<Integer, Integer>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int key = rand.nextInt();
            expected.put(key, key);
            actual.put(key, key);

            assertMapsEqual(expected, actual);

            if (rand.nextBoolean() && i > 0) {
                Object[] keys = expected.keySet().toArray();
                int keyToRemove = (Integer) keys[rand.nextInt(keys.length)];
                assertEquals(expected.remove(keyToRemove), actual.remove(keyToRemove));
                assertMapsEqual(expected, actual);
            }
        }
    }

}
