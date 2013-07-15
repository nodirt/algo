package algo.util;

public final class NumberTheory {
    private NumberTheory(){}

    public static boolean isPrime(int x) {
        int sqrt = (int) Math.sqrt(x);
        for (int i = 0; i < sqrt; i++) {
            if (x % sqrt == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static int ceilingPrime(int x) {
        while (!isPrime(x)) {
            x++;
        }
        return x;
    }
    
    public static int gcd(int x, int y) {
        if (x > y) {
            return gcd(y, x - y);
        } else if (x > 0) {
            return gcd(x, y - x);
        } else {
            return y;
        }
    }
}
