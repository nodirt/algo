package algo.hash.chaining;

import java.util.Random;

public class UniversalHashing extends Reduce {
    private final long mFactor;
    int mPower = 2;

    public UniversalHashing() {
        super(4);
        Random rand = new Random();
        mFactor = rand.nextInt();
    }

    @Override
    public int increaseSize() {
        mPower++;
        return super.increaseSize();
    }

    @Override
    public int reduce(int key) {
        long result = ((key * mFactor) & 0x0000FFFF) >> (32 - mPower);
        assert result >= 0 && result < mSize;
        return (int) result;
    }
}
