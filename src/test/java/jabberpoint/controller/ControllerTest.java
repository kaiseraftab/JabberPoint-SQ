package jabberpoint.controller;

import jabberpoint.command.Command;
import jabberpoint.command.NextSlideCommand;
import jabberpoint.command.PrevSlideCommand;
import jabberpoint.model.Presentation;
import jabberpoint.model.Slide;
import jabberpoint.model.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Presentation presentation;
    private Component dummyComponent;

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
        dummyComponent = new Canvas();
    }

    // KeyController tests
    @Test
    void testKeyControllerCreation() {
        Map<Integer, Command> commands = new HashMap<>();
        KeyController controller = new KeyController(commands);
        assertNotNull(controller);
    }

    @Test
    void testKeyControllerExecutesNextCommand() {
        Command next = new NextSlideCommand(presentation);
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(KeyEvent.VK_PAGE_DOWN, next);

        KeyController controller = new KeyController(commands);
        KeyEvent event = new KeyEvent(dummyComponent, KeyEvent.KEY_PRESSED, 
                System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, KeyEvent.CHAR_UNDEFINED);

        controller.keyPressed(event);
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testKeyControllerExecutesPrevCommand() {
        presentation.setSlideNumber(2);
        Command prev = new PrevSlideCommand(presentation);
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(KeyEvent.VK_PAGE_UP, prev);

        KeyController controller = new KeyController(commands);
        KeyEvent event = new KeyEvent(dummyComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_UP, KeyEvent.CHAR_UNDEFINED);

        controller.keyPressed(event);
        assertEquals(1, presentation.getSlideNumber());
    }

    @Test
    void testKeyControllerIgnoresUnmappedKey() {
        Map<Integer, Command> commands = new HashMap<>();
        commands.put(KeyEvent.VK_PAGE_DOWN, new NextSlideCommand(presentation));

        KeyController controller = new KeyController(commands);
        KeyEvent event = new KeyEvent(dummyComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_F1, KeyEvent.CHAR_UNDEFINED);

        controller.keyPressed(event);
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    void testKeyControllerMultipleKeys() {
        Command next = new NextSlideCommand(presentation);
        Command prev = new PrevSlideCommand(presentation);

        Map<Integer, Command> commands = new HashMap<>();
        commands.put(KeyEvent.VK_PAGE_DOWN, next);
        commands.put(KeyEvent.VK_PAGE_UP, prev);

        KeyController controller = new KeyController(commands);

        KeyEvent downEvent = new KeyEvent(dummyComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_DOWN, KeyEvent.CHAR_UNDEFINED);
        controller.keyPressed(downEvent);
        controller.keyPressed(downEvent);
        assertEquals(2, presentation.getSlideNumber());

        KeyEvent upEvent = new KeyEvent(dummyComponent, KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(), 0, KeyEvent.VK_PAGE_UP, KeyEvent.CHAR_UNDEFINED);
        controller.keyPressed(upEvent);
        assertEquals(1, presentation.getSlideNumber());
    }

    // MenuController tests
    @Test
    void testMenuControllerCreation() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("Open", new NextSlideCommand(presentation));
        commands.put("New", new NextSlideCommand(presentation));
        commands.put("Save", new NextSlideCommand(presentation));
        commands.put("Exit", new NextSlideCommand(presentation));
        commands.put("Next", new NextSlideCommand(presentation));
        commands.put("Prev", new PrevSlideCommand(presentation));
        commands.put("Go to", new NextSlideCommand(presentation));
        commands.put("About", new NextSlideCommand(presentation));

        MenuController controller = new MenuController(null, commands);
        assertNotNull(controller);
    }

    @Test
    void testMenuControllerHasMenus() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("Open", new NextSlideCommand(presentation));
        commands.put("New", new NextSlideCommand(presentation));
        commands.put("Save", new NextSlideCommand(presentation));
        commands.put("Exit", new NextSlideCommand(presentation));
        commands.put("Next", new NextSlideCommand(presentation));
        commands.put("Prev", new PrevSlideCommand(presentation));
        commands.put("Go to", new NextSlideCommand(presentation));
        commands.put("About", new NextSlideCommand(presentation));

        MenuController controller = new MenuController(null, commands);
        assertTrue(controller.getMenuCount() > 0);
    }

    @Test
    void testMenuControllerHasThreeMenus() {
        Map<String, Command> commands = new HashMap<>();
        commands.put("Open", new NextSlideCommand(presentation));
        commands.put("New", new NextSlideCommand(presentation));
        commands.put("Save", new NextSlideCommand(presentation));
        commands.put("Exit", new NextSlideCommand(presentation));
        commands.put("Next", new NextSlideCommand(presentation));
        commands.put("Prev", new PrevSlideCommand(presentation));
        commands.put("Go to", new NextSlideCommand(presentation));
        commands.put("About", new NextSlideCommand(presentation));

        MenuController controller = new MenuController(null, commands);
        assertEquals(3, controller.getMenuCount());
    }
}
