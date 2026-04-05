package jabberpoint.command;

import jabberpoint.accessor.Accessor;
import jabberpoint.accessor.XMLAccessor;
import jabberpoint.model.Presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Command pattern: concrete command with Accessor as receiver (per instructor feedback)
public class SaveFileCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public SaveFileCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, "savedPresentation.xml");
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}