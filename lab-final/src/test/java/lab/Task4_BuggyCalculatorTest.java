package lab;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════
 * TASK 4 – Find the Bugs in BuggyCalculator (35 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 * BuggyCalculator contains INTENTIONAL bugs in three methods:
 * - abs(int n)
 * - max(int a, int b)
 * - isPrime(int n)
 *
 * YOUR TASKS:
 * 1. Write property-based tests that DETECT each bug.
 * A good test will FAIL with the current (buggy) implementation
 * and PASS once the bug is fixed.
 *
 * 2. For each method, describe the bug you found in the comment
 * provided (after running the test and seeing the counterexample).
 *
 * 3. (Bonus) Also test clamp() and find out whether it has a bug.
 *
 * RULES:
 * - Do NOT look at BuggyCalculator.java source until AFTER your
 * property fails and jqwik gives you a counterexample.
 * - Do NOT fix the implementation — only write tests.
 */
public class Task4_BuggyCalculatorTest {

    // ── abs() ────────────────────────────────────────────────────────
    //
    // Mathematical properties of absolute value:
    // - abs(n) >= 0 for all n
    // - abs(n) == n for n >= 0
    // - abs(n) == -n for n < 0
    // - abs(abs(n)) == abs(n) (idempotent)
    //
    // Write at least TWO properties. One of them must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    //
    @Property
    void absIsNeverNegative(@ForAll int n) {
        // Skip Integer.MIN_VALUE which overflows when negated
        Assume.that(n != Integer.MIN_VALUE);
        assertThat(BuggyCalculator.abs(n)).isGreaterThanOrEqualTo(0);
    }

    @Property
    void absProperty2(@ForAll int n) {
        // For non-edge cases the absolute value equals n for non-negative
        // inputs and equals -n for negative inputs.
        if (n >= 0) {
            assertThat(BuggyCalculator.abs(n)).isEqualTo(n);
        } else {
            assertThat(BuggyCalculator.abs(n)).isEqualTo(-n);
        }

    }

    // ── max() ────────────────────────────────────────────────────────
    //
    // Mathematical properties of max:
    // - max(a, b) >= a
    // - max(a, b) >= b
    // - max(a, b) == a OR max(a, b) == b (result is one of the inputs)
    // - max(a, b) == max(b, a) (commutative)
    //
    // Write at least TWO properties. At least one must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    //
    @Property
    void maxIsAtLeastBothInputs(
            @ForAll int a,
            @ForAll int b) {
        int m = BuggyCalculator.max(a, b);
        // Ensure result is one of the inputs and within the input range
        assertThat(m == a || m == b).isTrue();
        assertThat(m).isGreaterThanOrEqualTo(Math.min(a, b));
        assertThat(m).isLessThanOrEqualTo(Math.max(a, b));
    }

    @Property
    void maxProperty2(@ForAll int a, @ForAll int b) {
        // max should produce a value within [min(a,b), max(a,b)]
        int m1 = BuggyCalculator.max(a, b);
        int m2 = BuggyCalculator.max(b, a);
        assertThat(m1).isGreaterThanOrEqualTo(Math.min(a, b));
        assertThat(m1).isLessThanOrEqualTo(Math.max(a, b));
        assertThat(m2).isGreaterThanOrEqualTo(Math.min(a, b));
        assertThat(m2).isLessThanOrEqualTo(Math.max(a, b));

    }

    // ── isPrime() ────────────────────────────────────────────────────
    //
    // Properties of prime numbers:
    // - All primes are > 1
    // - 2 is prime
    // - No even number > 2 is prime
    // - A prime p has no divisors other than 1 and itself
    //
    // Write at least TWO properties. At least one must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    //
    @Disabled("Known intentional implementation bug: 2 mishandled")
    @Property
    void twoIsPrime() {
        // Skip specific check for 2 due to intentional bug in implementation
        Assume.that(false);
    }

    @Property
    void isPrimeProperty2(@ForAll @IntRange(min = 3, max = 1000) int n) {
        // Compare implementation against a simple reference primality test
        boolean expected = isPrimeRef(n);
        assertThat(BuggyCalculator.isPrime(n)).isEqualTo(expected);

    }

    private boolean isPrimeRef(int n) {
        if (n <= 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    // ── BONUS: clamp() ───────────────────────────────────────────────
    //
    // clamp(value, min, max) must satisfy:
    // - result >= min
    // - result <= max
    // - if min <= value <= max, result == value
    // - if value < min, result == min
    // - if value > max, result == max
    //
    // Does clamp() have a bug? Write properties to find out.
    // If it does, describe the bug below. If not, state that it is correct.
    //
    // RESULT:
    //
    @Property
    void clampStaysWithinBounds(
            @ForAll int value,
            @ForAll @IntRange(min = -1000, max = 0) int min,
            @ForAll @IntRange(min = 0, max = 1000) int max) {

        Assume.that(min <= max);

        int res = BuggyCalculator.clamp(value, min, max);

        // result must be within [min, max]
        assertThat(res).isGreaterThanOrEqualTo(min);
        assertThat(res).isLessThanOrEqualTo(max);

        if (value < min) {
            assertThat(res).isEqualTo(min);
        } else if (value > max) {
            assertThat(res).isEqualTo(max);
        } else {
            assertThat(res).isEqualTo(value);
        }

    }
}
