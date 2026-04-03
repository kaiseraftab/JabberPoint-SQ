package jabberpoint.command;

import jabberpoint.model.Presentation;

// Command pattern: concrete command with Presentation as receiver
public class NewPresentationCommand implements Command {
    private Presentation presentation;

    public NewPresentationCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.clear();
    }
}