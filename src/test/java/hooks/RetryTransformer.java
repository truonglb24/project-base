package hooks;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class RetryTransformer implements IAnnotationTransformer {

    private static final ConcurrentHashMap<Method, Integer> RETRIES = new ConcurrentHashMap<>();

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        if (testMethod == null) return;

        Flaky flaky = testMethod.getAnnotation(Flaky.class);
        if (flaky == null) return;

        RETRIES.put(testMethod, Math.max(0, flaky.retries()));
        annotation.setRetryAnalyzer(ParameterizedRetryAnalyzer.class);
    }

    public static class ParameterizedRetryAnalyzer implements IRetryAnalyzer {
        private int count = 0;

        @Override
        public boolean retry(ITestResult result) {
            Method m = result.getMethod().getConstructorOrMethod().getMethod();
            int max = RETRIES.getOrDefault(m, 0);
            if (count < max) {
                count++;
                return true;
            }
            return false;
        }
    }
}
