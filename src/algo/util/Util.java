package algo.util;

import java.util.Random;

import algo.sort.RandomSorting;

public final class Util {
    static final Random RANDOM = new Random();

    private Util() {}
    
    // Arrays 
    
    public static final <E> void swap(E[] array, int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    public static <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].compareTo(array[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static final Integer[] box(int[] nums) {
        Integer[] objs = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            objs[i] = nums[i];
        }
        return objs;
    }
    
    public static final int[] unbox(Integer[] objs) {
        int[] nums = new int[objs.length];
        for (int i = 0; i < objs.length; i++) {
            nums[i] = objs[i];
        }
        return nums;
    }

    // Random numbers
    
    public static int[] randomOrder(int count) {
        Integer[] nums = new Integer[count];
        for (int i = 0; i < count; i++) {
            nums[i] = i; 
        }
        RandomSorting<Integer> rand = new RandomSorting<Integer>();
        rand.sort(nums);
        return Util.unbox(nums);
    }
    
    public static int[] randomIntegers(int count, int max) {
        int[] nums = new int[count];
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextInt(max);
        }
        return nums;
    }
    
    // Number theory

    public static boolean isPrime(int x) {
        int sqrt = (int) Math.sqrt(x);
        for (int i = 0; i < sqrt; i++) {
            if (x % sqrt == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static int ceilingPrime(int x) {
        while (!isPrime(x)) {
            x++;
        }
        return x;
    }
    
    public static int gcd(int x, int y) {
        if (x > y) {
            return gcd(y, x - y);
        } else if (x > 0) {
            return gcd(x, y - x);
        } else {
            return y;
        }
    }
}
