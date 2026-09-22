package dev.inditex.ci.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JsonStringsTest {

    @Test
    void quotesNullAsNullLiteral() {
        assertEquals("null", JsonStrings.quote(null));
    }

    @Test
    void escapesQuotesAndControlCharacters() {
        assertEquals("\"a\\\"b\\n\\t\"", JsonStrings.quote("a\"b\n\t"));
    }

    @Test
    void detectsUnbalancedStrings() {
        assertFalse(JsonStrings.isBalanced("say \"hi"));
        assertTrue(JsonStrings.isBalanced("say \"hi\""));
        assertTrue(JsonStrings.isBalanced("say \"a\\\"b\""));
    }
}
