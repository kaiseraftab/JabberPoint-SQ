package jabberpoint.command;

import jabberpoint.view.AboutBox;
import java.awt.Frame;

// Command pattern: receiver is AboutBox (per instructor feedback)
public class ShowAboutCommand implements Command {
    private Frame parent;

    public ShowAboutCommand(Frame parent) {
        this.parent = parent;
    }

    @Override
    public void execute() {
        AboutBox.show(parent);
    }
}