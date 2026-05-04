package dev.inditex.ci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StringUtils {

    private static final Logger LOG = LoggerFactory.getLogger(StringUtils.class);

    private StringUtils() {
    }

    public static String reverse(String input) {
        if (input == null) {
            LOG.debug("Received null input");
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }

    public static boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String normalized = input.toLowerCase().replaceAll("\\s+", "");
        return normalized.equals(reverse(normalized));
    }

    public static boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
