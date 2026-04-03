package jabberpoint.controller;

import jabberpoint.command.Command;
import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.util.Map;

// SRP: MenuController only builds the menu UI. Business logic is in Command classes.
// OCP: Adding a new menu action = adding a new Command. This class doesn't change.
// DIP: Depends on Command interface, not concrete action implementations.
public class MenuController extends MenuBar {

	private Frame parent;
	private static final long serialVersionUID = 227L;

	protected static final String FILE = "File";
	protected static final String VIEW = "View";
	protected static final String HELP = "Help";

	public MenuController(Frame frame, Map<String, Command> commands) {
		parent = frame;

		// File menu
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(mkMenuItem("Open", commands.get("Open")));
		fileMenu.add(mkMenuItem("New", commands.get("New")));
		fileMenu.add(mkMenuItem("Save", commands.get("Save")));
		fileMenu.addSeparator();
		fileMenu.add(mkMenuItem("Exit", commands.get("Exit")));
		add(fileMenu);

		// View menu
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(mkMenuItem("Next", commands.get("Next")));
		viewMenu.add(mkMenuItem("Prev", commands.get("Prev")));
		viewMenu.add(mkMenuItem("Go to", commands.get("Go to")));
		add(viewMenu);

		// Help menu
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(mkMenuItem("About", commands.get("About")));
		setHelpMenu(helpMenu);
	}

	private MenuItem mkMenuItem(String name, Command command) {
		MenuItem menuItem = new MenuItem(name, new MenuShortcut(name.charAt(0)));
		menuItem.addActionListener(actionEvent -> command.execute());
		return menuItem;
	}
}