package jabberpoint.command;

// Command pattern: ExitCommand receiver is JabberPoint (per instructor feedback)
// SRP: Application exit is no longer in the Presentation model class
public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
}