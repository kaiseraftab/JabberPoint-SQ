package jabberpoint.factory;

import jabberpoint.model.BitmapItem;
import jabberpoint.model.SlideItem;
import jabberpoint.model.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    private SlideItemFactory factory;

    @BeforeEach
    void setUp() {
        factory = new ConcreteSlideItemFactory();
    }

    @Test
    void testCreateTextItem() {
        SlideItem item = factory.createSlideItem("text", 1, "Hello");
        assertNotNull(item);
        assertTrue(item instanceof TextItem);
    }

    @Test
    void testCreateBitmapItem() {
        SlideItem item = factory.createSlideItem("image", 1, "JabberPoint.gif");
        assertNotNull(item);
        assertTrue(item instanceof BitmapItem);
    }

    @Test
    void testCreateTextItemLevel() {
        SlideItem item = factory.createSlideItem("text", 3, "Test");
        assertEquals(3, item.getLevel());
    }

    @Test
    void testCreateTextItemContent() {
        SlideItem item = factory.createSlideItem("text", 1, "Hello World");
        assertTrue(item instanceof TextItem);
        assertEquals("Hello World", ((TextItem) item).getText());
    }

    @Test
    void testCreateBitmapItemName() {
        SlideItem item = factory.createSlideItem("image", 2, "JabberPoint.gif");
        assertTrue(item instanceof BitmapItem);
        assertEquals("JabberPoint.gif", ((BitmapItem) item).getName());
    }

    @Test
    void testCreateUnknownTypeReturnsNull() {
        SlideItem item = factory.createSlideItem("video", 1, "test.mp4");
        assertNull(item);
    }

    @Test
    void testFactoryImplementsInterface() {
        assertTrue(factory instanceof SlideItemFactory);
    }

    @Test
    void testCreateMultipleItems() {
        SlideItem text = factory.createSlideItem("text", 1, "Hello");
        SlideItem image = factory.createSlideItem("image", 2, "JabberPoint.gif");
        assertNotNull(text);
        assertNotNull(image);
        assertNotSame(text, image);
    }
}
