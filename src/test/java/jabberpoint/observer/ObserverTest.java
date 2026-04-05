package jabberpoint.observer;

import jabberpoint.model.Presentation;
import jabberpoint.model.Slide;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObserverTest {

    @Test
    void testPresentationImplementsSubject() {
        Presentation presentation = new Presentation();
        assertTrue(presentation instanceof PresentationSubject);
    }

    @Test
    void testObserverReceivesCorrectPresentation() {
        Presentation presentation = new Presentation();
        Presentation[] received = {null};

        PresentationObserver observer = (pres, slide) -> received[0] = pres;

        presentation.append(new Slide());
        presentation.addObserver(observer);
        presentation.setSlideNumber(0);

        assertSame(presentation, received[0]);
    }

    @Test
    void testObserverReceivesCorrectSlide() {
        Presentation presentation = new Presentation();
        Slide testSlide = new Slide();
        testSlide.setTitle("Observer Test Slide");
        Slide[] received = {null};

        PresentationObserver observer = (pres, slide) -> received[0] = slide;

        presentation.append(testSlide);
        presentation.addObserver(observer);
        presentation.setSlideNumber(0);

        assertNotNull(received[0]);
        assertEquals("Observer Test Slide", received[0].getTitle());
    }

    @Test
    void testMultipleAddAndRemove() {
        Presentation presentation = new Presentation();
        int[] count = {0};

        PresentationObserver obs1 = (pres, slide) -> count[0]++;
        PresentationObserver obs2 = (pres, slide) -> count[0]++;
        PresentationObserver obs3 = (pres, slide) -> count[0]++;

        presentation.append(new Slide());
        presentation.addObserver(obs1);
        presentation.addObserver(obs2);
        presentation.addObserver(obs3);
        presentation.removeObserver(obs2);

        presentation.setSlideNumber(0);

        assertEquals(2, count[0]);
    }

    @Test
    void testNotifyOnNextSlide() {
        Presentation presentation = new Presentation();
        boolean[] called = {false};

        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(0);

        presentation.addObserver((pres, slide) -> called[0] = true);
        presentation.nextSlide();

        assertTrue(called[0]);
    }

    @Test
    void testNotifyOnPrevSlide() {
        Presentation presentation = new Presentation();
        boolean[] called = {false};

        presentation.append(new Slide());
        presentation.append(new Slide());
        presentation.setSlideNumber(1);

        presentation.addObserver((pres, slide) -> called[0] = true);
        presentation.prevSlide();

        assertTrue(called[0]);
    }
}
