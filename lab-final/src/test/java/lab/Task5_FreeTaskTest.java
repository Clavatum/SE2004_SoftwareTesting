package lab;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import static org.assertj.core.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════
 * TASK 5 – Free Design Task (30 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 * Choose ONE method from FreeTask.java:
 *
 * Option A – caesarEncrypt(String text, int shift)
 * Option B – average(List<Double> values)
 * Option C – fizzBuzz(int n)
 * Option D – compress(String s)
 *
 * STEP 1: Write which option you chose and WHY in the comment below.
 * STEP 2: List at least THREE properties you identified (in comments).
 * STEP 3: Implement those properties as jqwik tests below.
 *
 * Grading criteria:
 * [1 pt] Correct option declared and reasoning given.
 * [2 pt] At least 3 meaningful, distinct properties listed.
 * [3 pt] All listed properties implemented as runnable @Property tests.
 * [1 pt] Tests pass with the correct implementation.
 * [1 pt] Data generation is purposeful (not just @ForAll String).
 * [1 pt] At least one property uses a @Provide method or Combinators.
 *
 * ────────────────────────────────────────────────────────────────────
 * CHOSEN OPTION:
 * (Write A, B, C, or D here)
 *
 * REASON FOR CHOICE:
 * A
 *
 * REASON FOR CHOICE:
 * I chose Option A (Caesar cipher) because it has clear,
 * testable algebraic properties: rotations by 26 are identity,
 * encryption is invertible by negating the shift, and letter
 * vs non-letter handling and case-preservation are well-specified.
 *
 * PROPERTIES IDENTIFIED:
 * 1. Shifting by a multiple of 26 is identity: encrypt(text,26)==text.
 * 2. Encryption is invertible: encrypt(encrypt(text,s),-s)==text.
 * 3. Non-letter characters are unchanged and letter case is preserved.
 *
 * (Tests below implement these properties; one uses a @Provide generator.)
 *
 * ────────────────────────────────────────────────────────────────────
 */
public class Task5_FreeTaskTest {

    // ── PROPERTY 1 ───────────────────────────────────────────────────
    @Provide
    net.jqwik.api.Arbitrary<String> printableStrings() {
        return Arbitraries.strings().withCharRange(' ', '~').ofMinLength(0).ofMaxLength(200);
    }

    @Property
    void shiftBy26IsIdentity(@ForAll("printableStrings") String text) {
        // shifting by 26 (full alphabet rotation) should be identity
        assertThat(FreeTask.caesarEncrypt(text, 26)).isEqualTo(text);
        assertThat(FreeTask.caesarEncrypt(text, -26)).isEqualTo(text);
    }

    // ── PROPERTY 2 ───────────────────────────────────────────────────
    @Property
    void invertibleByNegatingShift(@ForAll("printableStrings") String text,
            @ForAll @IntRange(min = -1000, max = 1000) int shift) {
        String enc = FreeTask.caesarEncrypt(text, shift);
        String dec = FreeTask.caesarEncrypt(enc, -shift);
        assertThat(dec).isEqualTo(text);
    }

    // ── PROPERTY 3 ───────────────────────────────────────────────────
    @Property
    void nonLettersUnchangedAndCasePreserved(@ForAll("printableStrings") String text,
            @ForAll @IntRange(min = -52, max = 52) int shift) {
        String out = FreeTask.caesarEncrypt(text, shift);

        // length preserved
        assertThat(out.length()).isEqualTo(text.length());

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char o = out.charAt(i);
            if (!Character.isLetter(c)) {
                // non-letters must remain exactly the same
                assertThat(o).isEqualTo(c);
            } else {
                // letters must remain letters and preserve case
                assertThat(Character.isLetter(o)).isTrue();
                assertThat(Character.isUpperCase(o)).isEqualTo(Character.isUpperCase(c));
            }
        }
    }

    // Add more @Property methods if you identified additional properties.
}
