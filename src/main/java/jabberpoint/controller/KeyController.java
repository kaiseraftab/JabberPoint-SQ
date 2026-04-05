package jabberpoint.controller;

import jabberpoint.command.Command;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Map;

// SRP: KeyController only handles keyboard input. Business logic is in Command classes.
// DIP: Depends on Command interface, not concrete implementations.
public class KeyController extends KeyAdapter {
	private Map<Integer, Command> commands;

	public KeyController(Map<Integer, Command> commands) {
		this.commands = commands;
	}

	public void keyPressed(KeyEvent keyEvent) {
		Command command = commands.get(keyEvent.getKeyCode());
		if (command != null) {
			command.execute();
		}
	}
}