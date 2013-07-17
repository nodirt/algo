package algo.util;

import java.util.*;
import java.util.Map.Entry;

import algo.sort.Shuffle;

public final class Util {
    static final Random RANDOM = new Random();
    public static final double GOLDEN_RATIO = (1 + Math.sqrt(5.0)) / 2;

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

    // Collections

    public static boolean equal(Object a, Object b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }


    public static <K, V> boolean assertMapsEqual(Map<K, V> expected, Map<K, V> actual) {
        assert expected.size() == actual.size();

        for (Entry<K, V> entry : expected.entrySet()) {
            assert equal(entry.getValue(), actual.get(entry.getKey()));
        }

        assert expected.keySet().equals(actual.keySet());
        assert new HashSet<V>(expected.values()).equals(new HashSet<V>(actual.values()));

        return true;
    }


    // Random numbers

    public static int[] randomOrder(int count) {
        Integer[] nums = new Integer[count];
        for (int i = 0; i < count; i++) {
            nums[i] = i;
        }
        Shuffle<Integer> rand = new Shuffle<Integer>();
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

    public static int[] randomIntegers(int max) {
        return randomIntegers(max, max);
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
