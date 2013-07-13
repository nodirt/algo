package algo.sort;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import algo.Repeat;

public class OrderStatisticTest extends SortingTestBase {
    @Test
    @Repeat(count=1000)
    public void orderStatistic() {
        Integer[] unordered = randomOrder();
        Integer[] ordered = unordered.clone();
        Arrays.sort(ordered);
        
        OrderStatistic<Integer> ranker = new OrderStatistic<Integer>();
        Random rand = new Random();
        int rank = rand.nextInt(ordered.length);
        Assert.assertEquals(ordered[rank], ranker.findSmallest(unordered, rank));
    }
}
