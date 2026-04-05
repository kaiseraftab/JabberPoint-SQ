package jabberpoint.command;

import jabberpoint.accessor.Accessor;
import jabberpoint.accessor.XMLAccessor;
import jabberpoint.model.Presentation;
import java.awt.Frame;
import java.io.IOException;
import javax.swing.JOptionPane;

// Command pattern: concrete command with Accessor as receiver (per instructor feedback)
public class OpenFileCommand implements Command {
    private Presentation presentation;
    private Frame parent;

    public OpenFileCommand(Presentation presentation, Frame parent) {
        this.presentation = presentation;
        this.parent = parent;
    }

    @Override
    public void execute() {
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, "test.xml");
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parent, "IO Exception: " + exc,
                    "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}