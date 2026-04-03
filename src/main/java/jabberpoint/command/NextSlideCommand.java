package jabberpoint.command;

import jabberpoint.model.Presentation;

// SRP: This class only handles the "next slide" action
// Command pattern: concrete command with Presentation as receiver
public class NextSlideCommand implements Command {
    private Presentation presentation;

    public NextSlideCommand(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public void execute() {
        presentation.nextSlide();
    }
}