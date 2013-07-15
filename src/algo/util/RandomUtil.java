package algo.util;

import java.util.*;

import algo.sort.RandomSorting;

public final class RandomUtil {
    static final Random RANDOM = new Random();
    
    public static int[] randomOrder(int count) {
        Integer[] nums = new Integer[count];
        for (int i = 0; i < count; i++) {
            nums[i] = i; 
        }
        RandomSorting<Integer> rand = new RandomSorting<Integer>();
        rand.sort(nums);
        return ArrayUtil.unbox(nums);
    }
    
    public static int[] randomIntegers(int count, int max) {
        int[] nums = new int[count];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(max);
        }
        return nums;
    }

}
