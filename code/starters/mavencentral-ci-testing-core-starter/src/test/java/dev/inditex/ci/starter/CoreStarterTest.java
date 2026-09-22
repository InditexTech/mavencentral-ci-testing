package dev.inditex.ci.starter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CoreStarterTest {

    @Test
    void reversesThroughTheCoreFacade() {
        assertEquals("cba", new CoreStarter().reverse("abc"));
    }

    @Test
    void preservesCoreNullHandling() {
        assertNull(new CoreStarter().reverse(null));
    }

    @Test
    void acceptsACustomFacade() {
        CoreStarter starter = new CoreStarter(input -> "fixed");
        assertEquals("fixed", starter.reverse("anything"));
    }
}
