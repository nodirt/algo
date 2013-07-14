package algo.hash.openAddressing;

import java.util.*;

public class QuadraticProbing extends ProbingStrategy {
	Random mRand = new Random();
	int mC1;
	int mC2;
	
	public QuadraticProbing() {
	}
	public QuadraticProbing(int size) {
		super(size);
	}
	
	@Override
	public int setSize(int value) {
		int size = super.setSize(value);
		mC1 = 1 + mRand.nextInt(size - 1);
		mC2 = mRand.nextInt(size);
		return size;
	}
	
	@Override
	public Iterator<Integer> probe(final int key) {
		return new Iterator<Integer>() {
			int i = 0;
			
			@Override
			public boolean hasNext() {
				return true;
			}
			
			@Override
			public Integer next() {
				int result = modSize(key + i * mC1 + i * i * mC2);
				i++;
				return result;
			}
			
			@Override
			public void remove() {
			}
			
		};
	}
}
