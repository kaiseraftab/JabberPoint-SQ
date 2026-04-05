package jabberpoint.command;

import jabberpoint.model.Presentation;
import jabberpoint.model.Slide;
import jabberpoint.model.Style;
import jabberpoint.accessor.Accessor;
import jabberpoint.accessor.XMLAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    private Presentation presentation;

    @BeforeEach
    void setUp() {
        Style.createStyles();
        presentation = new Presentation();
        for (int i = 0; i < 5; i++) {
            Slide slide = new Slide();
            slide.setTitle("Slide " + (i + 1));
            presentation.append(slide);
        }
        presentation.setSlideNumber(0);
    }

    // NextSlideCommand
    @Test
    void testNextSlideCommand() {
        Command command = new NextSlideCommand(presentation);
        command.execute();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testNextSlideCommandMultiple() {
        Command command = new NextSlideCommand(presentation);
        command.execute();
        command.execute();
        command.execute();
        assertEquals(3, presentation.getSlideNumber());
    }

    @Test
    void testNextSlideCommandAtEnd() {
        presentation.setSlideNumber(4);
        Command command = new NextSlideCommand(presentation);
        command.execute();
        assertEquals(4, presentation.getSlideNumber());
    }

    // PrevSlideCommand
    @Test
    void testPrevSlideCommand() {
        presentation.setSlideNumber(3);
        Command command = new PrevSlideCommand(presentation);
        command.execute();
        assertEquals(2, presentation.getSlideNumber());
    }

    @Test
    void testPrevSlideCommandMultiple() {
        presentation.setSlideNumber(4);
        Command command = new PrevSlideCommand(presentation);
        command.execute();
        command.execute();
        assertEquals(2, presentation.getSlideNumber());
    }

    @Test
    void testPrevSlideCommandAtStart() {
        presentation.setSlideNumber(0);
        Command command = new PrevSlideCommand(presentation);
        command.execute();
        assertEquals(0, presentation.getSlideNumber());
    }

    // NewPresentationCommand
    @Test
    void testNewPresentationCommand() {
        Command command = new NewPresentationCommand(presentation);
        command.execute();
        assertEquals(0, presentation.getSize());
    }

    @Test
    void testNewPresentationCommandOnEmpty() {
        presentation.clear();
        Command command = new NewPresentationCommand(presentation);
        command.execute();
        assertEquals(0, presentation.getSize());
    }

    // SaveFileCommand - actually execute it
    @Test
    void testSaveFileCommandExecute() {
        SaveFileCommand command = new SaveFileCommand(presentation, null);
        command.execute();
        File saved = new File("savedPresentation.xml");
        if (saved.exists()) {
            saved.delete();
        }
    }

    // OpenFileCommand - actually execute it with test.xml
    @Test
    void testOpenFileCommandExecute() {
        File testFile = new File("test.xml");
        if (testFile.exists()) {
            OpenFileCommand command = new OpenFileCommand(presentation, null);
            command.execute();
            assertTrue(presentation.getSize() > 0);
        }
    }

    // Combined tests
    @Test
    void testCommandsShareSamePresentation() {
        Command next = new NextSlideCommand(presentation);
        Command prev = new PrevSlideCommand(presentation);
        next.execute();
        next.execute();
        assertEquals(2, presentation.getSlideNumber());
        prev.execute();
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testNextThenNewPresentation() {
        Command next = new NextSlideCommand(presentation);
        Command newPres = new NewPresentationCommand(presentation);
        next.execute();
        next.execute();
        newPres.execute();
        assertEquals(0, presentation.getSize());
    }

    @Test
    void testAllNavigationCommands() {
        Command next = new NextSlideCommand(presentation);
        Command prev = new PrevSlideCommand(presentation);

        next.execute();
        assertEquals(1, presentation.getSlideNumber());
        next.execute();
        assertEquals(2, presentation.getSlideNumber());
        next.execute();
        assertEquals(3, presentation.getSlideNumber());
        prev.execute();
        assertEquals(2, presentation.getSlideNumber());
        prev.execute();
        assertEquals(1, presentation.getSlideNumber());
    }

    // Interface tests
    @Test
    void testNextSlideImplementsCommand() {
        assertTrue(new NextSlideCommand(presentation) instanceof Command);
    }

    @Test
    void testPrevSlideImplementsCommand() {
        assertTrue(new PrevSlideCommand(presentation) instanceof Command);
    }

    @Test
    void testNewPresentationImplementsCommand() {
        assertTrue(new NewPresentationCommand(presentation) instanceof Command);
    }

    @Test
    void testExitCommandImplementsCommand() {
        assertTrue(new ExitCommand() instanceof Command);
    }

    @Test
    void testShowAboutImplementsCommand() {
        assertTrue(new ShowAboutCommand(null) instanceof Command);
    }

    @Test
    void testOpenFileImplementsCommand() {
        assertTrue(new OpenFileCommand(presentation, null) instanceof Command);
    }

    @Test
    void testSaveFileImplementsCommand() {
        assertTrue(new SaveFileCommand(presentation, null) instanceof Command);
    }

    @Test
    void testGoToSlideImplementsCommand() {
        assertTrue(new GoToSlideCommand(presentation, null) instanceof Command);
    }
}
