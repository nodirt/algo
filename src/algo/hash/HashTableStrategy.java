package algo.hash;

public abstract class HashTableStrategy {
    protected int mSize;

    protected HashTableStrategy(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        mSize = size;
    }

    public int size() {
        return mSize;
    }

    public int increaseSize() {
        mSize *= 2;
        return mSize;
    }

    protected int modSize(int key) {
        return Math.abs(key % mSize);
    }

}
