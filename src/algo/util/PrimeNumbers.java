package algo.util;

public final class PrimeNumbers {
    private PrimeNumbers(){}

    public static boolean isPrime(int x) {
        int sqrt = (int) Math.sqrt(x);
        for (int i = 0; i < sqrt; i++) {
            if (x % sqrt == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static int ceiling(int x) {
        while (!isPrime(x)) {
            x++;
        }
        return x;
    }
}
