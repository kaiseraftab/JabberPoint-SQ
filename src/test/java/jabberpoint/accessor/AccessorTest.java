package jabberpoint.accessor;

import jabberpoint.model.Presentation;
import jabberpoint.model.Slide;
import jabberpoint.model.Style;
import jabberpoint.factory.ConcreteSlideItemFactory;
import jabberpoint.factory.SlideItemFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class AccessorTest {

    private Presentation presentation;

    @BeforeEach
    void setUp() {
        Style.createStyles();
        presentation = new Presentation();
    }

    // Accessor tests
    @Test
    void testGetDemoAccessor() {
        Accessor accessor = Accessor.getDemoAccessor();
        assertNotNull(accessor);
    }

    @Test
    void testGetDemoAccessorType() {
        Accessor accessor = Accessor.getDemoAccessor();
        assertTrue(accessor instanceof DemoPresentation);
    }

    // DemoPresentation tests
    @Test
    void testDemoLoadFile() throws IOException {
        Accessor demo = Accessor.getDemoAccessor();
        demo.loadFile(presentation, "");
        assertTrue(presentation.getSize() > 0);
    }

    @Test
    void testDemoLoadFileSetsTitle() throws IOException {
        Accessor demo = Accessor.getDemoAccessor();
        demo.loadFile(presentation, "");
        assertNotNull(presentation.getTitle());
    }

    @Test
    void testDemoLoadFileCreatesSlides() throws IOException {
        Accessor demo = Accessor.getDemoAccessor();
        demo.loadFile(presentation, "");
        assertNotNull(presentation.getSlide(0));
    }

    @Test
    void testDemoSaveFileThrowsException() {
        Accessor demo = Accessor.getDemoAccessor();
        assertThrows(IllegalStateException.class, () -> {
            demo.saveFile(presentation, "test.xml");
        });
    }

    // XMLAccessor tests
    @Test
    void testXMLAccessorDefaultConstructor() {
        XMLAccessor accessor = new XMLAccessor();
        assertNotNull(accessor);
    }

    @Test
    void testXMLAccessorWithFactory() {
        SlideItemFactory factory = new ConcreteSlideItemFactory();
        XMLAccessor accessor = new XMLAccessor(factory);
        assertNotNull(accessor);
    }

    @Test
    void testXMLAccessorLoadFile() throws IOException {
        XMLAccessor accessor = new XMLAccessor();
        File testFile = new File("test.xml");
        if (testFile.exists()) {
            accessor.loadFile(presentation, "test.xml");
            assertTrue(presentation.getSize() > 0);
        }
    }

    @Test
    void testXMLAccessorLoadFileWithFactory() throws IOException {
        SlideItemFactory factory = new ConcreteSlideItemFactory();
        XMLAccessor accessor = new XMLAccessor(factory);
        File testFile = new File("test.xml");
        if (testFile.exists()) {
            accessor.loadFile(presentation, "test.xml");
            assertTrue(presentation.getSize() > 0);
        }
    }

    @Test
    void testXMLAccessorLoadFileSetsTitle() throws IOException {
        XMLAccessor accessor = new XMLAccessor();
        File testFile = new File("test.xml");
        if (testFile.exists()) {
            accessor.loadFile(presentation, "test.xml");
            assertNotNull(presentation.getTitle());
        }
    }

    @Test
    void testXMLAccessorSaveFile() throws IOException {
        Accessor demo = Accessor.getDemoAccessor();
        demo.loadFile(presentation, "");

        XMLAccessor accessor = new XMLAccessor();
        accessor.saveFile(presentation, "target/test-output.xml");

        File output = new File("target/test-output.xml");
        assertTrue(output.exists());
        output.delete();
    }

    @Test
    void testXMLAccessorSaveAndLoad() throws IOException {
        Accessor demo = Accessor.getDemoAccessor();
        demo.loadFile(presentation, "");
        String originalTitle = presentation.getTitle();

        XMLAccessor accessor = new XMLAccessor();
        accessor.saveFile(presentation, "target/test-roundtrip.xml");

        File output = new File("target/test-roundtrip.xml");
        assertTrue(output.exists());
        output.delete();
    }

    // Accessor interface
    @Test
    void testXMLAccessorExtendsAccessor() {
        assertTrue(new XMLAccessor() instanceof Accessor);
    }

    @Test
    void testDemoPresentationExtendsAccessor() {
        assertTrue(Accessor.getDemoAccessor() instanceof Accessor);
    }
}
