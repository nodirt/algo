package algo;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class RepeatedTestRunner extends BlockJUnit4ClassRunner {
    
    private class RepeatedInvokeMethod extends InvokeMethod {
        int _runCount;
        
        public RepeatedInvokeMethod(FrameworkMethod testMethod, Object target, int repeatCount) {
            super(testMethod, target);
            _runCount = repeatCount;
        }
        
        @Override
        public void evaluate() throws Throwable {
            for (int i = 0; i < _runCount; i++) {
                super.evaluate();
            }
        }
    }
    
    public RepeatedTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }
    
    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        ManyTimes manyTimes = method.getAnnotation(ManyTimes.class);
        if (manyTimes == null) {
            return super.methodInvoker(method, test);
        } else {
            return new RepeatedInvokeMethod(method, test, manyTimes.value());
        }
    }
}
