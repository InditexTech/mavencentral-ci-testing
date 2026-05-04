package dev.inditex.ci;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void reverseReturnsReversedString() {
        assertEquals("cba", StringUtils.reverse("abc"));
    }

    @Test
    void reverseReturnsNullForNullInput() {
        assertNull(StringUtils.reverse(null));
    }

    @Test
    void reverseHandlesEmptyString() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    void isPalindromeReturnsTrueForPalindrome() {
        assertTrue(StringUtils.isPalindrome("racecar"));
    }

    @Test
    void isPalindromeIgnoresCase() {
        assertTrue(StringUtils.isPalindrome("Madam"));
    }

    @Test
    void isPalindromeIgnoresSpaces() {
        assertTrue(StringUtils.isPalindrome("nurses run"));
    }

    @Test
    void isPalindromeReturnsFalseForNonPalindrome() {
        assertFalse(StringUtils.isPalindrome("hello"));
    }

    @Test
    void isPalindromeReturnsFalseForNull() {
        assertFalse(StringUtils.isPalindrome(null));
    }

    @Test
    void isPalindromeReturnsFalseForEmpty() {
        assertFalse(StringUtils.isPalindrome(""));
    }
}
