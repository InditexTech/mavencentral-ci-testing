package dev.inditex.ci.starter;

import dev.inditex.ci.StringUtils;

/**
 * Convenience facade over the core library, emulating a starter module in a
 * nested monorepo layout.
 */
public final class CoreStarter {

    private final StringUtilsFacade facade;

    /** Re-export of the core functionality this starter wraps. */
    public interface StringUtilsFacade {
        String reverse(String input);
    }

    public CoreStarter() {
        this(StringUtils::reverse);
    }

    public CoreStarter(StringUtilsFacade facade) {
        this.facade = facade;
    }

    public String reverse(String input) {
        return facade.reverse(input);
    }
}
