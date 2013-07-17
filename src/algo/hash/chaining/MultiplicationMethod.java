package algo.hash.chaining;

import static algo.util.Util.GOLDEN_RATIO;

public class MultiplicationMethod extends Reduce {
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
