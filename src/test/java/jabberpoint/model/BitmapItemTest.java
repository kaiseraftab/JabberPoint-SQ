package jabberpoint.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitmapItemTest {

    @Test
    void testConstructorSetsLevel() {
        BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
        assertEquals(1, item.getLevel());
    }

    @Test
    void testGetName() {
        BitmapItem item = new BitmapItem(2, "test.jpg");
        assertEquals("test.jpg", item.getName());
    }

    @Test
    void testToString() {
        BitmapItem item = new BitmapItem(2, "test.jpg");
        String result = item.toString();
        assertTrue(result.contains("BitmapItem"));
        assertTrue(result.contains("test.jpg"));
    }

    @Test
    void testHasDrawStrategy() {
        BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
        assertNotNull(item.drawStrategy);
    }
}
