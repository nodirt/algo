package algo.structures;

import java.util.*;

import org.junit.Test;

import algo.*;
import static org.junit.Assert.*;

public class DisjointSetTests extends BaseTestClass {
    @Test
    @ManyTimes
    public void test1() {
        final int n = 5;
        DisjointSet<Integer> dsu = new DisjointSet<Integer>();
        for (int i = 0; i < n; i++) {
            dsu.makeSet(i);
        }
        
        for (int i = 0; i < dsu.size(); i++) {
            for (int j = 0; j < dsu.size(); j++) {
                assertEquals(i == j, dsu.inSameSet(i, j));
            }
        }
        
        Random rand = new Random();
        int a = rand.nextInt(dsu.size());
        int b = rand.nextInt(dsu.size());
        
        dsu.unit(a, b);
        assertTrue(dsu.inSameSet(a, b));
        
        int c = rand.nextInt(dsu.size());
        int d = rand.nextBoolean() ? a : b;
        if (rand.nextBoolean()) {
            dsu.unit(c, d);
        } else {
            dsu.unit(d, c);
        }

        assertTrue(dsu.inSameSet(a, b));
        assertTrue(dsu.inSameSet(a, c));
    }
}
