package jabberpoint.view;

import jabberpoint.command.Command;
import jabberpoint.controller.KeyController;
import jabberpoint.controller.MenuController;
import jabberpoint.model.Presentation;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import javax.swing.JFrame;

// SRP: SlideViewerFrame only sets up the window and wires components together
public class SlideViewerFrame extends JFrame {
	private static final long serialVersionUID = 3227L;

	private static final String JABTITLE = "Jabberpoint 1.6 - OU";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	public SlideViewerFrame(String title, Presentation presentation,
							Map<String, Command> menuCommands, Map<Integer, Command> keyCommands) {
		super(title);
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		setupWindow(slideViewerComponent, menuCommands, keyCommands);
	}

	public void setupWindow(SlideViewerComponent slideViewerComponent,
							Map<String, Command> menuCommands, Map<Integer, Command> keyCommands) {
		setTitle(JABTITLE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(slideViewerComponent);
		addKeyListener(new KeyController(keyCommands));
		setMenuBar(new MenuController(this, menuCommands));
		setSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
	}
}