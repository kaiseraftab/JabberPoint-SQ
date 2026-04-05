package jabberpoint;

import jabberpoint.accessor.Accessor;
import jabberpoint.accessor.XMLAccessor;
import jabberpoint.command.Command;
import jabberpoint.command.ExitCommand;
import jabberpoint.command.GoToSlideCommand;
import jabberpoint.command.NewPresentationCommand;
import jabberpoint.command.NextSlideCommand;
import jabberpoint.command.OpenFileCommand;
import jabberpoint.command.PrevSlideCommand;
import jabberpoint.command.SaveFileCommand;
import jabberpoint.command.ShowAboutCommand;
import jabberpoint.model.Presentation;
import jabberpoint.model.Style;
import jabberpoint.view.SlideViewerFrame;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

// SRP: JabberPoint only handles application startup and wiring
// DIP: Commands depend on interfaces, not concrete classes
public class JabberPoint {
	protected static final String IOERR = "IO Error: ";
	protected static final String JABERR = "Jabberpoint Error ";
	protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";

	public static void main(String[] argv) {
		Style.createStyles();
		Presentation presentation = new Presentation();
		createAndShowApplication(presentation, argv);
	}

	private static void createAndShowApplication(Presentation presentation, String[] argv) {
		// Create Command objects (Command pattern)
		Command nextSlide = new NextSlideCommand(presentation);
		Command prevSlide = new PrevSlideCommand(presentation);
		Command newPresentation = new NewPresentationCommand(presentation);
		Command exitApp = new ExitCommand();

		// Menu commands - mapped by menu item name
		Map<String, Command> menuCommands = createMenuCommands(
				presentation, nextSlide, prevSlide, newPresentation, exitApp);

		// Key commands - mapped by key code
		Map<Integer, Command> keyCommands = createKeyCommands(
				nextSlide, prevSlide, exitApp);

		// Create the frame - pass command maps
		SlideViewerFrame frame = new SlideViewerFrame(JABVERSION, presentation,
				menuCommands, keyCommands);

		// Update commands that need the frame as parent
		menuCommands.put("Open", new OpenFileCommand(presentation, frame));
		menuCommands.put("Save", new SaveFileCommand(presentation, frame));
		menuCommands.put("Go to", new GoToSlideCommand(presentation, frame));
		menuCommands.put("About", new ShowAboutCommand(frame));

		loadPresentation(presentation, argv);
	}

	private static Map<String, Command> createMenuCommands(
			Presentation presentation, Command nextSlide, Command prevSlide,
			Command newPresentation, Command exitApp) {
		Map<String, Command> menuCommands = new HashMap<>();
		menuCommands.put("Open", new OpenFileCommand(presentation, null));
		menuCommands.put("New", newPresentation);
		menuCommands.put("Save", new SaveFileCommand(presentation, null));
		menuCommands.put("Exit", exitApp);
		menuCommands.put("Next", nextSlide);
		menuCommands.put("Prev", prevSlide);
		menuCommands.put("Go to", new GoToSlideCommand(presentation, null));
		menuCommands.put("About", new ShowAboutCommand(null));
		return menuCommands;
	}

	private static Map<Integer, Command> createKeyCommands(
			Command nextSlide, Command prevSlide, Command exitApp) {
		Map<Integer, Command> keyCommands = new HashMap<>();
		keyCommands.put(KeyEvent.VK_PAGE_DOWN, nextSlide);
		keyCommands.put(KeyEvent.VK_DOWN, nextSlide);
		keyCommands.put(KeyEvent.VK_ENTER, nextSlide);
		keyCommands.put((int) '+', nextSlide);
		keyCommands.put(KeyEvent.VK_PAGE_UP, prevSlide);
		keyCommands.put(KeyEvent.VK_UP, prevSlide);
		keyCommands.put((int) '-', prevSlide);
		keyCommands.put((int) 'q', exitApp);
		keyCommands.put((int) 'Q', exitApp);
		return keyCommands;
	}

	private static void loadPresentation(Presentation presentation, String[] argv) {
		try {
			if (argv.length == 0) {
				Accessor.getDemoAccessor().loadFile(presentation, "");
			} else {
				new XMLAccessor().loadFile(presentation, argv[0]);
			}
			presentation.setSlideNumber(0);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					IOERR + ex, JABERR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
}