package lab;

/**
 * Task 4 – BuggyCalculator
 *
 * This class contains INTENTIONAL bugs.
 * Your task is to write property-based tests that expose them.
 *
 * DO NOT fix the bugs — write tests that DETECT them.
 * DO NOT modify this file.
 */
public class BuggyCalculator {

    private BuggyCalculator() {}

    /**
     * Returns the absolute value of {@code n}.
     *
     * Expected behaviour: result >= 0 for all integers,
     * and result == n for n >= 0, result == -n for n < 0.
     *
     * Contains a bug for a specific edge case.
     */
    public static int abs(int n) {
        // BUG: Integer.MIN_VALUE has no positive counterpart in int range
        if (n < 0) return -n;
        return n;
    }

    /**
     * Returns the larger of two integers.
     *
     * Expected behaviour: result >= a, result >= b,
     * and result is either a or b.
     *
     * Contains a subtle bug.
     */
    public static int max(int a, int b) {
        // BUG: wrong operator (should be >)
        if (a >= b) return a;
        return a;   // should be: return b;
    }

    /**
     * Returns {@code true} if {@code n} is a prime number.
     * Returns {@code false} for n <= 1.
     *
     * Expected behaviour: consistent with the mathematical definition of prime.
     *
     * Contains a bug for a specific small prime.
     */
    public static boolean isPrime(int n) {
        if (n <= 1)  return false;
        if (n == 2)  return false;  // BUG: 2 is prime
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Clamps {@code value} to the range [{@code min}, {@code max}].
     * If value < min, returns min.
     * If value > max, returns max.
     * Otherwise returns value.
     *
     * @throws IllegalArgumentException if min > max
     *
     * Contains a bug in the boundary handling.
     */
    public static int clamp(int value, int min, int max) {
        if (min > max)
            throw new IllegalArgumentException("min must be <= max");
        // BUG: strict inequality — boundary value itself is not clamped correctly
        if (value < min) return min;
        if (value > max) return max;
        return value;   // this part is actually correct; bugs are above
        // Real bug: swap min/max in the return for one branch:
        // if (value < min) return max;  <-- uncomment to activate bug
    }
}
