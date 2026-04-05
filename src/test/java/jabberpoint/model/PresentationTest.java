package jabberpoint.model;

import jabberpoint.observer.PresentationObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PresentationTest {

    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
    }

    @Test
    void testNewPresentationIsEmpty() {
        assertEquals(0, presentation.getSize());
    }

    @Test
    void testSetAndGetTitle() {
        presentation.setTitle("Test Presentation");
        assertEquals("Test Presentation", presentation.getTitle());
    }

    @Test
    void testAppendSlide() {
        Slide slide = new Slide();
        slide.setTitle("Slide 1");
        presentation.append(slide);
        assertEquals(1, presentation.getSize());
    }

    @Test
    void testGetSlideReturnsCorrectSlide() {
        Slide slide = new Slide();
        slide.setTitle("My Slide");
        presentation.append(slide);
        assertEquals("My Slide", presentation.getSlide(0).getTitle());
    }

    @Test
    void testGetSlideOutOfBoundsReturnsNull() {
        assertNull(presentation.getSlide(99));
    }

    @Test
    void testGetSlideNegativeReturnsNull() {
        assertNull(presentation.getSlide(-1));
    }

    @Test
    void testNextSlide() {
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(0);
        presentation.nextSlide();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testNextSlideDoesNotExceedSize() {
        presentation.append(new Slide());
        presentation.setSlideNumber(0);
        presentation.nextSlide();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testPrevSlide() {
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(1);
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testPrevSlideDoesNotGoBelowZero() {
        presentation.append(new Slide());
        presentation.setSlideNumber(0);
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testClearRemovesAllSlides() {
        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.clear();
        assertEquals(0, presentation.getSize());
    }

    @Test
    void testGetCurrentSlide() {
        Slide slide = new Slide();
        slide.setTitle("Current");
        presentation.append(slide);
        presentation.setSlideNumber(0);
        assertNotNull(presentation.getCurrentSlide());
        assertEquals("Current", presentation.getCurrentSlide().getTitle());
    }

    @Test
    void testGetSlideNumber() {
        presentation.append(new Slide());
        presentation.setSlideNumber(0);
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testObserverGetsNotified() {
        boolean[] wasCalled = {false};
        PresentationObserver observer = (pres, slide) -> wasCalled[0] = true;

        presentation.append(new Slide());
        presentation.addObserver(observer);
        presentation.setSlideNumber(0);

        assertTrue(wasCalled[0]);
    }

    @Test
    void testMultipleObserversGetNotified() {
        int[] callCount = {0};
        PresentationObserver observer1 = (pres, slide) -> callCount[0]++;
        PresentationObserver observer2 = (pres, slide) -> callCount[0]++;

        presentation.append(new Slide());
        presentation.addObserver(observer1);
        presentation.addObserver(observer2);
        presentation.setSlideNumber(0);

        assertEquals(2, callCount[0]);
    }

    @Test
    void testRemovedObserverNotNotified() {
        boolean[] wasCalled = {false};
        PresentationObserver observer = (pres, slide) -> wasCalled[0] = true;

        presentation.append(new Slide());
        presentation.addObserver(observer);
        presentation.removeObserver(observer);
        presentation.setSlideNumber(0);

        assertFalse(wasCalled[0]);
    }

    @Test
    void testObserverReceivesCorrectSlide() {
        Slide[] received = {null};
        PresentationObserver observer = (pres, slide) -> received[0] = slide;

        Slide testSlide = new Slide();
        testSlide.setTitle("Test");
        presentation.append(testSlide);
        presentation.addObserver(observer);
        presentation.setSlideNumber(0);

        assertNotNull(received[0]);
        assertEquals("Test", received[0].getTitle());
    }
}
