package jabberpoint.command;

import jabberpoint.model.Presentation;

import javax.swing.*;
import java.awt.*;

// Command pattern: concrete command with Presentation as receiver
public class GoToSlideCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public GoToSlideCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        String pageNumberStr = JOptionPane.showInputDialog(parent, "Page number?");
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
    }
}