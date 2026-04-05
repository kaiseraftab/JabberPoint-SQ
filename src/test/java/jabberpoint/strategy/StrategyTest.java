package jabberpoint.strategy;

import jabberpoint.model.BitmapItem;
import jabberpoint.model.Style;
import jabberpoint.model.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {

    private Style style;
    private Graphics graphics;
    private ImageObserver observer;

    @BeforeEach
    void setUp() {
        Style.createStyles();
        style = Style.getStyle(0);
        BufferedImage image = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        observer = new Canvas();
    }

    @Test
    void testTextDrawStrategyImplementsInterface() {
        assertTrue(new TextDrawStrategy("test") instanceof DrawStrategy);
    }

    @Test
    void testBitmapDrawStrategyImplementsInterface() {
        assertTrue(new BitmapDrawStrategy(null) instanceof DrawStrategy);
    }

    @Test
    void testTextDrawStrategyGetText() {
        TextDrawStrategy strategy = new TextDrawStrategy("Hello");
        assertEquals("Hello", strategy.getText());
    }

    @Test
    void testTextDrawStrategyNullText() {
        TextDrawStrategy strategy = new TextDrawStrategy(null);
        assertEquals("", strategy.getText());
    }

    @Test
    void testTextDrawStrategyEmptyText() {
        TextDrawStrategy strategy = new TextDrawStrategy("");
        assertEquals("", strategy.getText());
    }

    @Test
    void testTextDrawStrategyGetAttributedString() {
        TextDrawStrategy strategy = new TextDrawStrategy("Hello World");
        assertNotNull(strategy.getAttributedString(style, 1.0f));
    }

    @Test
    void testTextDrawStrategyLongText() {
        String longText = "This is a very long text for testing";
        TextDrawStrategy strategy = new TextDrawStrategy(longText);
        assertEquals(longText, strategy.getText());
    }

    @Test
    void testMultipleStrategiesIndependent() {
        TextDrawStrategy s1 = new TextDrawStrategy("First");
        TextDrawStrategy s2 = new TextDrawStrategy("Second");
        assertEquals("First", s1.getText());
        assertEquals("Second", s2.getText());
    }

    @Test
    void testTextDrawStrategyDrawWithNullText() {
        TextDrawStrategy strategy = new TextDrawStrategy(null);
        strategy.draw(0, 0, 1.0f, graphics, style, observer);
    }

    @Test
    void testTextDrawStrategyDrawWithEmptyText() {
        TextDrawStrategy strategy = new TextDrawStrategy("");
        strategy.draw(0, 0, 1.0f, graphics, style, observer);
    }

    @Test
    void testTextDrawStrategyDrawWithText() {
        TextDrawStrategy strategy = new TextDrawStrategy("Hello World");
        strategy.draw(0, 0, 1.0f, graphics, style, observer);
    }

    @Test
    void testTextDrawStrategyGetBoundingBox() {
        TextDrawStrategy strategy = new TextDrawStrategy("Hello World");
        Rectangle box = strategy.getBoundingBox(graphics, observer, 1.0f, style);
        assertNotNull(box);
    }

    @Test
    void testBitmapDrawStrategyDraw() {
        BufferedImage img = new BufferedImage(100, 80, BufferedImage.TYPE_INT_RGB);
        BitmapDrawStrategy strategy = new BitmapDrawStrategy(img);
        strategy.draw(0, 0, 1.0f, graphics, style, observer);
    }

    @Test
    void testBitmapDrawStrategyGetBoundingBox() {
        BufferedImage img = new BufferedImage(100, 80, BufferedImage.TYPE_INT_RGB);
        BitmapDrawStrategy strategy = new BitmapDrawStrategy(img);
        Rectangle box = strategy.getBoundingBox(graphics, observer, 1.0f, style);
        assertNotNull(box);
        assertTrue(box.width > 0);
        assertTrue(box.height > 0);
    }

    @Test
    void testBitmapDrawStrategyDifferentScale() {
        BufferedImage img = new BufferedImage(100, 80, BufferedImage.TYPE_INT_RGB);
        BitmapDrawStrategy strategy = new BitmapDrawStrategy(img);
        Rectangle box1 = strategy.getBoundingBox(graphics, observer, 1.0f, style);
        Rectangle box2 = strategy.getBoundingBox(graphics, observer, 2.0f, style);
        assertTrue(box2.width > box1.width);
    }

    @Test
    void testTextItemCreatesWithStrategy() {
        TextItem item = new TextItem(1, "Test");
        assertNotNull(item);
        assertEquals("Test", item.getText());
    }

    @Test
    void testBitmapItemCreatesWithStrategy() {
        BitmapItem item = new BitmapItem(1, "JabberPoint.gif");
        assertNotNull(item);
        assertEquals("JabberPoint.gif", item.getName());
    }

    @Test
    void testDifferentItemTypes() {
        TextItem textItem = new TextItem(1, "Test");
        BitmapItem bitmapItem = new BitmapItem(1, "JabberPoint.gif");
        assertNotSame(textItem.getClass(), bitmapItem.getClass());
    }
}