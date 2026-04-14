package lab;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import static org.assertj.core.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════
 *  TASK 2 – Writing Properties for countOccurrences()  (35 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 *  Complete the TODO sections below to implement five property-based
 *  tests for StringUtils.countOccurrences().
 *
 *  Rules:
 *  - Do NOT use concrete string literals like "hello" as test data.
 *  - Do NOT hard-code expected values unless they follow from the
 *    structure of your generated input.
 *  - Every @Property must compile and pass with the correct implementation.
 *
 *  Hint: Review the lecture notes (Part 1, Example 1) for patterns.
 */
public class Task2_CountOccurrencesTest {

    // ── PROPERTY 1: result is never negative ─────────────────────────
    //
    // For any valid (text, sub) pair, the count must be >= 0.
    //
    @Property
    void resultIsNeverNegative(
            @ForAll @StringLength(min = 0, max = 200) String text,
            @ForAll @StringLength(min = 1, max = 20)  String sub) {

        // TODO: call countOccurrences and assert the result is >= 0
        // (one line is enough)

    }

    // ── PROPERTY 2: zero occurrences when character sets are disjoint ─
    //
    // Generate text from characters 'a'-'m' and sub from 'n'-'z'.
    // The count must be 0.
    //
    @Property
    void zeroOccurrencesWithDisjointCharSets(
            // TODO: declare the 'text' parameter with appropriate @ForAll constraints
            // TODO: declare the 'sub' parameter with appropriate @ForAll constraints
    ) {

        // TODO: assert that countOccurrences returns 0

    }

    // ── PROPERTY 3: count equals n when text = sub.repeat(n) ─────────
    //
    // Construct text by repeating sub exactly n times.
    // The count must equal n.
    //
    @Property
    void countEqualsRepetitionFactor(
            @ForAll @StringLength(min = 1, max = 10)
            @CharRange(from = 'a', to = 'z') String sub,
            @ForAll @IntRange(min = 1, max = 50) int n) {

        // TODO: build 'text' and assert the correct count

    }

    // ── PROPERTY 4: adding a non-matching prefix/suffix doesn't change count
    //
    // If you wrap text with characters that don't appear in sub,
    // the count should remain the same.
    //
    @Property
    void wrappingWithNonMatchingCharsDoesNotChangeCount(
            @ForAll @StringLength(min = 0, max = 80)
            @CharRange(from = 'a', to = 'e') String text,
            @ForAll @StringLength(min = 1, max = 5)
            @CharRange(from = 'a', to = 'e') String sub,
            @ForAll @StringLength(min = 1, max = 20)
            @CharRange(from = 'v', to = 'z') String wrapper) {

        // TODO: compute baseline count, then count with wrapper added
        //       before and after text, then assert they are equal

    }

    // ── PROPERTY 5: invalid arguments throw IllegalArgumentException ──
    //
    // If text is null, or sub is null, or sub is empty,
    // countOccurrences must throw IllegalArgumentException.
    //
    // Hint: use Assume.that() to filter inputs.
    //       Use assertThatThrownBy(...).isInstanceOf(...)
    //
    @Property
    void invalidArgumentsThrowException(
            @ForAll @WithNull(0.4) String text,
            @ForAll @WithNull(0.4) String sub) {

        Assume.that(text == null || sub == null || (sub != null && sub.isEmpty()));

        // TODO: assert that calling countOccurrences throws IllegalArgumentException

    }
}
