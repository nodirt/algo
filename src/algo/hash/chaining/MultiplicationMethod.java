package algo.hash.chaining;

public class MultiplicationMethod extends Reduce {
    private final double GOLDEN_RATIO = (1 + Math.sqrt(5.0)) / 2;
    
    public MultiplicationMethod(int size) {
        super(size);
    }
    public MultiplicationMethod() {
        this(4);
    }

    @Override
    public int reduce(int key) {
        return (int) (Math.abs((GOLDEN_RATIO * key) % 1) * size());
    }
    
}
