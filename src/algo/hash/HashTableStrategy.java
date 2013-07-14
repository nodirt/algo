package algo.hash;

public abstract class HashTableStrategy {
    int mSize;
    
    protected HashTableStrategy(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        setSize(size);
    }
    
    public int getSize() {
        return mSize;
    }
    public int setSize(int value) {
        mSize = value;
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
