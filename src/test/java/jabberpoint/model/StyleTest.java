package jabberpoint.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StyleTest {

    @BeforeEach
    void setUp() {
        Style.createStyles();
    }

    @Test
    void testCreateStylesNotNull() {
        assertNotNull(Style.getStyle(0));
    }

    @Test
    void testGetStyleReturnsStyleForEachLevel() {
        for (int i = 0; i < 5; i++) {
            assertNotNull(Style.getStyle(i));
        }
    }

    @Test
    void testGetStyleBeyondMaxReturnsLastStyle() {
        Style style = Style.getStyle(10);
        assertNotNull(style);
    }

    @Test
    void testStyleHasIndent() {
        Style style = Style.getStyle(0);
        assertEquals(0, style.getIndent());
    }

    @Test
    void testStyleHasColor() {
        Style style = Style.getStyle(0);
        assertNotNull(style.getColor());
    }

    @Test
    void testStyleHasFont() {
        Style style = Style.getStyle(0);
        assertNotNull(style.getFont(1.0f));
    }

    @Test
    void testGetFont() {
        Style style = Style.getStyle(0);
        assertNotNull(style.getFont(1.0f));
    }

    @Test
    void testToString() {
        Style style = Style.getStyle(0);
        String result = style.toString();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
