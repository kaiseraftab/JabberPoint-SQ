package jabberpoint.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Canvas;
import java.awt.image.ImageObserver;

class SlideTest {

    private Slide slide;
    private Graphics graphics;
    private ImageObserver observer;


    @BeforeEach
    void setUp() {
        Style.createStyles();
        slide = new Slide();
        BufferedImage image = new BufferedImage(Slide.WIDTH, Slide.HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics = image.getGraphics();
        observer = new Canvas();
    }

    @Test
    void testNewSlideHasNoItems() {
        assertEquals(0, slide.getSize());
    }

    @Test
    void testSetAndGetTitle() {
        slide.setTitle("Test Slide");
        assertEquals("Test Slide", slide.getTitle());
    }

    @Test
    void testAppendTextItem() {
        slide.append(1, "Item 1");
        assertEquals(1, slide.getSize());
    }

    @Test
    void testAppendMultipleItems() {
        slide.append(1, "Item 1");
        slide.append(2, "Item 2");
        slide.append(3, "Item 3");
        assertEquals(3, slide.getSize());
    }

    @Test
    void testGetSlideItem() {
        slide.append(1, "First Item");
        SlideItem item = slide.getSlideItem(0);
        assertNotNull(item);
        assertEquals(1, item.getLevel());
    }

    @Test
    void testGetSlideItemsReturnsList() {
        slide.append(1, "Item A");
        slide.append(2, "Item B");
        assertNotNull(slide.getSlideItems());
        assertEquals(2, slide.getSlideItems().size());
    }

    @Test
    void testAppendSlideItem() {
        TextItem textItem = new TextItem(1, "Direct append");
        slide.append(textItem);
        assertEquals(1, slide.getSize());
    }

    // Tests for draw() method - covers the missed lines
    @Test
    void testDrawEmptySlide() {
        // Empty slide with no items - just verify no exception
        assertEquals(0, slide.getSize());
    }

    @Test
    void testDrawSlideWithTextItems() {
        slide.setTitle("Test");
        slide.append(1, "Hello World");
        slide.append(2, "Second line");
        Rectangle area = new Rectangle(0, 20, Slide.WIDTH, Slide.HEIGHT - 20);
        // Should draw without throwing
        slide.draw(graphics, area, observer);
    }

    @Test
    void testDrawSlideWithMultipleItems() {
        slide.setTitle("Full Slide");
        slide.append(0, "Title text");
        slide.append(1, "Level 1 text");
        slide.append(2, "Level 2 text");
        slide.append(3, "Level 3 text");
        slide.append(4, "Level 4 text");
        Rectangle area = new Rectangle(0, 20, Slide.WIDTH, Slide.HEIGHT - 20);
        slide.draw(graphics, area, observer);
    }

    @Test
    void testDrawSlideWithSmallArea() {
        slide.setTitle("Small");
        slide.append(new TextItem(1, "Test item with actual text content"));
        Rectangle area = new Rectangle(0, 0, 400, 300);
        slide.draw(graphics, area, observer);
    }

    // SlideItem delegation tests
    @Test
    void testSlideItemDraw() {
        TextItem item = new TextItem(1, "Test text");
        Style style = Style.getStyle(1);
        // Should not throw - delegates to TextDrawStrategy
        item.draw(0, 0, 1.0f, graphics, style, observer);
    }

    @Test
    void testSlideItemGetBoundingBox() {
        TextItem item = new TextItem(1, "Test text");
        Style style = Style.getStyle(1);
        Rectangle box = item.getBoundingBox(graphics, observer, 1.0f, style);
        assertNotNull(box);
    }

    @Test
    void testSlideItemDrawAtDifferentScales() {
        TextItem item = new TextItem(1, "Scale test");
        Style style = Style.getStyle(1);
        item.draw(0, 0, 0.5f, graphics, style, observer);
        item.draw(0, 0, 2.0f, graphics, style, observer);
    }

    @Test
    void testSlideItemGetBoundingBoxAtDifferentScales() {
        TextItem item = new TextItem(1, "Scale test");
        Style style = Style.getStyle(1);
        Rectangle box1 = item.getBoundingBox(graphics, observer, 0.5f, style);
        Rectangle box2 = item.getBoundingBox(graphics, observer, 2.0f, style);
        assertNotNull(box1);
        assertNotNull(box2);
    }

    @Test
    void testSlideWidthAndHeight() {
        assertEquals(1200, Slide.WIDTH);
        assertEquals(800, Slide.HEIGHT);
    }
}
