package algo.tree;

public class Visitor<N> {
    boolean mInterrupted;
    
    public void interrupt() {
        mInterrupted = true;
    }
    public boolean isInterrupted() {
        return mInterrupted;
    }
    
    public void pre(N node) {}
    public void post(N node) {}
}
