package lab;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import static org.assertj.core.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════
 *  TASK 4 – Find the Bugs in BuggyCalculator  (35 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 *  BuggyCalculator contains INTENTIONAL bugs in three methods:
 *    - abs(int n)
 *    - max(int a, int b)
 *    - isPrime(int n)
 *
 *  YOUR TASKS:
 *  1. Write property-based tests that DETECT each bug.
 *     A good test will FAIL with the current (buggy) implementation
 *     and PASS once the bug is fixed.
 *
 *  2. For each method, describe the bug you found in the comment
 *     provided (after running the test and seeing the counterexample).
 *
 *  3. (Bonus) Also test clamp() and find out whether it has a bug.
 *
 *  RULES:
 *  - Do NOT look at BuggyCalculator.java source until AFTER your
 *    property fails and jqwik gives you a counterexample.
 *  - Do NOT fix the implementation — only write tests.
 */
public class Task4_BuggyCalculatorTest {

    // ── abs() ────────────────────────────────────────────────────────
    //
    // Mathematical properties of absolute value:
    //   - abs(n) >= 0  for all n
    //   - abs(n) == n  for n >= 0
    //   - abs(n) == -n for n < 0
    //   - abs(abs(n)) == abs(n)  (idempotent)
    //
    // Write at least TWO properties. One of them must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    //
    @Property
    void absIsNeverNegative(@ForAll int n) {
        // TODO

    }

    @Property
    void absProperty2(/* TODO: parameters */) {
        // TODO: write a second property that catches the actual bug

    }

    // ── max() ────────────────────────────────────────────────────────
    //
    // Mathematical properties of max:
    //   - max(a, b) >= a
    //   - max(a, b) >= b
    //   - max(a, b) == a  OR  max(a, b) == b  (result is one of the inputs)
    //   - max(a, b) == max(b, a)  (commutative)
    //
    // Write at least TWO properties. At least one must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    //
    @Property
    void maxIsAtLeastBothInputs(
            @ForAll int a,
            @ForAll int b) {
        // TODO

    }

    @Property
    void maxProperty2(/* TODO: parameters */) {
        // TODO

    }

    // ── isPrime() ────────────────────────────────────────────────────
    //
    // Properties of prime numbers:
    //   - All primes are > 1
    //   - 2 is prime
    //   - No even number > 2 is prime
    //   - A prime p has no divisors other than 1 and itself
    //
    // Write at least TWO properties. At least one must catch the bug.
    //
    // BUG FOUND (fill in after jqwik reports a counterexample):
    //
    @Property
    void twoIsPrime() {
        // TODO: this one does not need any @ForAll parameter —
        //       just directly assert that isPrime(2) is true.

    }

    @Property
    void isPrimeProperty2(/* TODO: parameters */) {
        // TODO

    }

    // ── BONUS: clamp() ───────────────────────────────────────────────
    //
    // clamp(value, min, max) must satisfy:
    //   - result >= min
    //   - result <= max
    //   - if min <= value <= max, result == value
    //   - if value < min, result == min
    //   - if value > max, result == max
    //
    // Does clamp() have a bug? Write properties to find out.
    // If it does, describe the bug below. If not, state that it is correct.
    //
    // RESULT:
    //
    @Property
    void clampStaysWithinBounds(
            @ForAll int value,
            @ForAll @IntRange(min = -1000, max = 0)   int min,
            @ForAll @IntRange(min = 0,    max = 1000) int max) {

        Assume.that(min <= max);

        // TODO

    }
}
