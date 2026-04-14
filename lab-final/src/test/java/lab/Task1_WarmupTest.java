package lab;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import static org.assertj.core.api.Assertions.*;

/**
 * ═══════════════════════════════════════════════════════════════════
 * TASK 1 – Warmup: Read and Understand Property-Based Tests (20 min)
 * ═══════════════════════════════════════════════════════════════════
 *
 * This file contains READY-TO-RUN property-based tests for StringUtils.
 *
 * YOUR TASKS:
 * 1. Run all tests and verify they pass (right-click → Run, or: mvn test)
 * 2. For each @Property method, read the code carefully and answer the
 * questions in the comments above it.
 * 3. Add a @Report(Reporting.GENERATED) annotation to ONE property of
 * your choice, re-run, and observe the generated inputs in the console.
 *
 * DO NOT change the logic of the tests — only add annotations/comments.
 */
public class Task1_WarmupTest {

        // ── PROPERTY 1 ──────────────────────────────────────────────────
        /*
         * QUESTION A: What does this property test?
         * Write your answer here (1–2 sentences):
         *
         * YOUR ANSWER:
         * Verifies that when `text` and `sub` are drawn from disjoint
         * character ranges, `sub` cannot appear in `text` and the count is 0.
         *
         * QUESTION B: Why do we use two separate @CharRange annotations
         * (one for 'text', another for 'sub')?
         * Write your answer here:
         *
         * YOUR ANSWER:
         * Each annotation constrains the characters for the corresponding
         * parameter independently, ensuring the two generated strings use
         * different character domains (so they cannot overlap).
         *
         */
        @Property
        void zeroOccurrencesWhenCharsDontOverlap(
                        @ForAll @StringLength(min = 0, max = 100) @CharRange(from = 'a', to = 'd') String text,
                        @ForAll @StringLength(min = 1, max = 10) @CharRange(from = 'x', to = 'z') String sub) {

                assertThat(StringUtils.countOccurrences(text, sub))
                                .isEqualTo(0);
        }

        // ── PROPERTY 2 ──────────────────────────────────────────────────
        /*
         * QUESTION C: How does this property guarantee that the expected
         * count is known before calling countOccurrences()?
         * Write your answer here:
         *
         * YOUR ANSWER:
         * The test constructs `text` as `sub.repeat(n)` so the number of
         * non-overlapping occurrences is exactly `n` by construction.
         *
         * QUESTION D: What is the name of the general PBT pattern used here?
         * (Hint: it was covered in the lecture.)
         * Write your answer here:
         *
         * YOUR ANSWER:
         * By-construction (constructive testing) — building inputs so the
         * expected outcome is known ahead of the call.
         *
         */
        @Property
        void exactCountByConstruction(
                        @ForAll @StringLength(min = 1, max = 8) @CharRange(from = 'a', to = 'z') String sub,
                        @ForAll @IntRange(min = 1, max = 30) int n) {

                String text = sub.repeat(n);

                assertThat(StringUtils.countOccurrences(text, sub))
                                .isEqualTo(n);
        }

        // ── PROPERTY 3 ──────────────────────────────────────────────────
        /*
         * QUESTION E: What invariant does this property verify?
         *
         * YOUR ANSWER:
         * That reversing is an involution: reversing twice returns the
         * original string (reverse(reverse(s)) == s).
         *
         * QUESTION F: Could this property ever produce a false failure
         * (i.e., fail even though the implementation is correct)?
         * Explain why or why not.
         *
         * YOUR ANSWER:
         * Unlikely — given valid (non-null) strings the property is exact;
         * a correct implementation cannot fail it unless there is an
         * external issue (e.g. unexpected nulls).
         *
         */
        @Property
        void reverseOfReversedIsOriginal(
                        @ForAll @StringLength(min = 0, max = 200) String s) {

                assertThat(StringUtils.reverse(StringUtils.reverse(s)))
                                .isEqualTo(s);
        }

        // ── PROPERTY 4 ──────────────────────────────────────────────────
        /*
         * QUESTION G: This property uses @Report(Reporting.GENERATED).
         * What does it print? Add it to a property of YOUR CHOICE
         * above, re-run the tests, and paste two lines of output here.
         *
         * YOUR ANSWER:
         * Line 1: timestamp = 2026-04-14T11:31:51.571745400, Task1
         * WarmupTest:palindromeIsItsOwnReverse =
         * Line 2: tries = 500 | # of calls to property
         *
         */
        @Property(tries = 500)
        @Report(Reporting.GENERATED)
        void palindromeIsItsOwnReverse(
                        @ForAll @StringLength(min = 1, max = 50) @CharRange(from = 'a', to = 'z') String half) {

                // Build a palindrome by concatenation: "abcba"
                String palindrome = half + StringUtils.reverse(half);

                assertThat(StringUtils.isPalindrome(palindrome))
                                .isTrue();
        }
}
