package co.com.jsierra.model.commons;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegularExpressionTest {

    @Test
    public void testConstructor() {
        assertThrows(IllegalStateException.class, () -> new RegularExpression());
    }

}