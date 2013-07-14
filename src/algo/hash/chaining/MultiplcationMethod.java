package algo.hash.chaining;

public class MultiplcationMethod extends Reduce {
    private final double GOLDEN_RATIO = (1 + Math.sqrt(5.0)) / 2;
    
    public MultiplcationMethod(int size) {
        super(size);
    }
    public MultiplcationMethod() {
        this(4);
    }

    @Override
    public int reduce(int key) {
        return (int) (Math.abs((GOLDEN_RATIO * key) % 1) * size());
    }
    
}
