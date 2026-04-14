package lab;

/**
 * Task 1 & Task 2 – StringUtils
 *
 * A simple string utility class used in the PBT Lab.
 * DO NOT modify this file.
 */
public class StringUtils {

    private StringUtils() {}

    /**
     * Returns the number of non-overlapping occurrences of {@code sub}
     * inside {@code text}.
     *
     * @param text the string to search in
     * @param sub  the substring to search for (must not be null or empty)
     * @return the count of non-overlapping occurrences (>= 0)
     * @throws IllegalArgumentException if {@code text} or {@code sub} is null,
     *                                  or if {@code sub} is empty
     */
    public static int countOccurrences(String text, String sub) {
        if (text == null || sub == null)
            throw new IllegalArgumentException("Arguments must not be null");
        if (sub.isEmpty())
            throw new IllegalArgumentException("sub must not be empty");

        int count = 0;
        int idx   = 0;
        while ((idx = text.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();   // non-overlapping: skip past current match
        }
        return count;
    }

    /**
     * Reverses the characters of the given string.
     *
     * @param s the string to reverse (must not be null)
     * @return the reversed string
     * @throws IllegalArgumentException if {@code s} is null
     */
    public static String reverse(String s) {
        if (s == null)
            throw new IllegalArgumentException("Argument must not be null");
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Returns {@code true} if the given string is a palindrome
     * (reads the same forwards and backwards), ignoring case.
     *
     * @param s the string to test (must not be null)
     * @throws IllegalArgumentException if {@code s} is null
     */
    public static boolean isPalindrome(String s) {
        if (s == null)
            throw new IllegalArgumentException("Argument must not be null");
        String lower = s.toLowerCase();
        return lower.equals(new StringBuilder(lower).reverse().toString());
    }
}
