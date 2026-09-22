package dev.inditex.ci.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minimal JSON string helpers kept dependency-free for the Maven Central CI
 * testing emulation of a nested monorepo module.
 */
public final class JsonStrings {

    private static final Logger LOG = LoggerFactory.getLogger(JsonStrings.class);

    private JsonStrings() {
    }

    public static String quote(String input) {
        if (input == null) {
            LOG.debug("Quoting null input");
            return "null";
        }
        StringBuilder sb = new StringBuilder(input.length() + 2);
        sb.append('"');
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> {
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        sb.append('"');
        return sb.toString();
    }

    public static boolean isBalanced(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }
        boolean inString = false;
        boolean escaped = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (escaped) {
                escaped = false;
                continue;
            }
            if (inString) {
                if (c == '\\') {
                    escaped = true;
                } else if (c == '"') {
                    inString = false;
                }
            } else if (c == '"') {
                inString = true;
            }
        }
        return !inString;
    }
}
