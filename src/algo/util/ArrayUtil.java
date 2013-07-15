package algo.util;

public class ArrayUtil {

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
}
