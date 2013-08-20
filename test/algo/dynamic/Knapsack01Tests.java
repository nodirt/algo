package algo.dynamic;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;
import algo.*;
import static algo.util.Util.*;

public class Knapsack01Tests extends BaseTestClass {
    int getTotalWeight(Integer[] indexes, int[] weights) {
        int totalWeight = 0;
        for (int i : indexes) {
            totalWeight += weights[i];
        }
        return totalWeight;
    }
    
    int bruteForce(int[] weights, int size) {
        Integer[] allIndexes = box(range(weights.length));
        
        int maxWeight = 0;
        for (Integer[] indexes : Combinatorics.allSubsequences(allIndexes)) {
            int totalWeight = getTotalWeight(indexes, weights);
            if (totalWeight <= size && totalWeight > maxWeight) {
                maxWeight = totalWeight;
            }
        }
        
        return maxWeight;
    }
    
    @Test
    @ManyTimes
    public void knapsackTest() {
        Random rand = new Random();
        int size = 10 + rand.nextInt(100);
        int[] weights = new int[rand.nextInt(10)];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = rand.nextInt(100);
        }
        
        Knapsack01 knapsack = new Knapsack01();
        int expected = bruteForce(weights, size);
        int actual = getTotalWeight(box(knapsack.solve(weights, size)), weights);
        assertEquals(expected, actual);
    }
}
