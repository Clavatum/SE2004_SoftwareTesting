package lab;

import java.util.List;

/**
 * Task 5 – FreeTask
 *
 * Choose ONE of the methods below and write property-based tests for it.
 * You do NOT need to implement the methods — they are already implemented.
 * Your job is to identify and test the properties.
 *
 * DO NOT modify this file.
 */
public class FreeTask {

    private FreeTask() {}

    /**
     * Option A – Caesar Cipher
     *
     * Encrypts {@code text} using a Caesar cipher with the given {@code shift}.
     * Only ASCII letters (a-z, A-Z) are shifted; other characters are unchanged.
     * The shift wraps around (e.g., shift=1 turns 'z' into 'a').
     *
     * @param text  the text to encrypt (must not be null)
     * @param shift the number of positions to shift (any integer, positive or negative)
     */
    public static String caesarEncrypt(String text, int shift) {
        if (text == null) throw new IllegalArgumentException("text must not be null");
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                sb.append((char) (base + (c - base + shift % 26 + 26) % 26));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Option B – Running Average
     *
     * Given a list of doubles, returns their arithmetic mean.
     *
     * @param values a non-null, non-empty list of doubles
     * @throws IllegalArgumentException if the list is null or empty
     */
    public static double average(List<Double> values) {
        if (values == null || values.isEmpty())
            throw new IllegalArgumentException("values must not be null or empty");
        return values.stream().mapToDouble(Double::doubleValue).average().orElseThrow();
    }

    /**
     * Option C – Fizz Buzz
     *
     * Returns "Fizz" if n is divisible by 3,
     *         "Buzz" if n is divisible by 5,
     *         "FizzBuzz" if divisible by both,
     *         the string representation of n otherwise.
     */
    public static String fizzBuzz(int n) {
        if (n % 15 == 0) return "FizzBuzz";
        if (n % 3  == 0) return "Fizz";
        if (n % 5  == 0) return "Buzz";
        return String.valueOf(n);
    }

    /**
     * Option D – String Compression (Run-Length Encoding)
     *
     * Compresses a string using run-length encoding.
     * e.g., "aaabbc" -> "a3b2c1"
     * An empty string returns an empty string.
     *
     * @param s the string to compress (must not be null)
     */
    public static String compress(String s) {
        if (s == null) throw new IllegalArgumentException("s must not be null");
        if (s.isEmpty()) return "";
        StringBuilder sb  = new StringBuilder();
        int           cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
            } else {
                sb.append(s.charAt(i - 1)).append(cnt);
                cnt = 1;
            }
        }
        sb.append(s.charAt(s.length() - 1)).append(cnt);
        return sb.toString();
    }
}
