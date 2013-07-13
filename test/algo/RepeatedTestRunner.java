package algo;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class RepeatedTestRunner extends BlockJUnit4ClassRunner {
    public RepeatedTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }
    
    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        Repeat repeat = method.getAnnotation(Repeat.class);
        int repeatCount = repeat != null ? repeat.count() : 1;
        
        for (int i = 0; i < repeatCount; i++) {
            super.runChild(method, notifier);
        }        
    }
}
