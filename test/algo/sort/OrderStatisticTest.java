package algo.sort;

import java.util.*;

import org.junit.*;

import algo.ManyTimes;

public class OrderStatisticTest extends SortingTestBase {
    @Test
    @ManyTimes(value = 100)
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
