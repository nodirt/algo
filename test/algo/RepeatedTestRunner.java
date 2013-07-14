package algo;

import org.junit.experimental.theories.Theories;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class RepeatedTestRunner extends Theories {
    
    public RepeatedTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }
    
    @Override
    public Statement methodBlock(FrameworkMethod method) {
        final Statement block = super.methodBlock(method); 

        final ManyTimes manyTimes = method.getAnnotation(ManyTimes.class);
        if (manyTimes == null) {
            return block;
        }
            
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                for (int i = 0; i < manyTimes.value(); i++) {
                    block.evaluate();
                }
            }
            
        };
    }
}
