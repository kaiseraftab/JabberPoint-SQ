package jabberpoint.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextItemTest {

    @Test
    void testConstructorSetsLevelAndText() {
        TextItem item = new TextItem(2, "Hello World");
        assertEquals(2, item.getLevel());
        assertEquals("Hello World", item.getText());
    }

    @Test
    void testEmptyConstructor() {
        TextItem item = new TextItem();
        assertEquals(0, item.getLevel());
    }

    @Test
    void testGetTextReturnsEmptyStringWhenNull() {
        TextItem item = new TextItem(0, null);
        assertEquals("", item.getText());
    }

    @Test
    void testGetTextReturnsText() {
        TextItem item = new TextItem(1, "Some text");
        assertEquals("Some text", item.getText());
    }

    @Test
    void testToString() {
        TextItem item = new TextItem(1, "Test");
        String result = item.toString();
        assertTrue(result.contains("TextItem"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("Test"));
    }

    @Test
    void testHasDrawStrategy() {
        TextItem item = new TextItem(1, "Test");
        assertNotNull(item.drawStrategy);
    }
}
