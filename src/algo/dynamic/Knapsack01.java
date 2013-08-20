package algo.dynamic;

import java.util.*;

public class Knapsack01 {
    
    public int[] solve(final int[] weights, final int maxWeight) {
        int[][] dp = new int[maxWeight + 1][weights.length + 1];
        for (int size = 1; size <= maxWeight; size++) {
            for (int count = 1; count <= weights.length; count++) {
                // without
                dp[size][count]= dp[size][count - 1];
                
                // with
                int weight = weights[count - 1];
                if (weight <= size) {
                    int with = dp[size - weight][count - 1] + weight;
                    if (with <= maxWeight && with > dp[size][count]) {
                        dp[size][count] = with;
                    }
                }
            }
        }
       
        Stack<Integer> solution = new Stack<Integer>();
        for (int count = weights.length, size = maxWeight; count > 0; count--) {
            int weight = weights[count - 1];
            if (weight <= size && dp[size - weight][count - 1] + weight == dp[size][count]) {
                solution.push(count - 1);
                size -= weight; 
            }
        }
        
        int[] result = new int[solution.size()];
        for (int i = 0; !solution.isEmpty(); i++) {
            result[i] = (int) solution.pop();
        }
        return result;
    }
}
