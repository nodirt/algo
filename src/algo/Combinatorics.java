package algo;

import java.lang.reflect.Array;
import java.util.*;

import algo.util.BaseIterator;

public class Combinatorics {
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T[]> allSubsequences(final T[] array) {
        final Class<?> componentType = array.getClass().getComponentType();

        class AllSubsets {
            class Iter extends BaseIterator<T[]> {
                final int count;
                final int suffixLength;
                Iterator<T[]> suffix;
                int state = 0;

                public Iter(int count, int suffixLength) {
                    if (count == 0) {
                        throw new IllegalArgumentException();
                    }
                    this.count = count;
                    this.suffixLength = suffixLength;
                }

                @Override
                protected boolean moveNext() {
                    switch (state) {
                    // initializing
                        case 0:
                            suffix = allSubsequences(count - 1, suffixLength).iterator();
                            state = 1;

                            // without
                        case 1:
                            if (suffix.hasNext()) {
                                mNext = suffix.next();
                                return true;
                            }
                            suffix = allSubsequences(count - 1, suffixLength + 1).iterator();
                            state = 2;

                            // with
                        case 2:
                            if (suffix.hasNext()) {
                                mNext = suffix.next();
                                mNext[mNext.length - 1 - this.suffixLength] = array[this.count - 1];
                                return true;
                            }
                            state = 3;
                    }

                    return false;
                }
            }

            public Iterable<T[]> allSubsequences(final int count, final int suffixLength) {
                if (count == 0) {
                    T[] result = (T[]) Array.newInstance(componentType, suffixLength);
                    return Arrays.<T[]>asList(result);
                }

                return new Iterable<T[]>() {

                    @Override
                    public Iterator<T[]> iterator() {
                        return new Iter(count, suffixLength);
                    }
                };
            }
        }

        return new AllSubsets().allSubsequences(array.length, 0);
    }
}
