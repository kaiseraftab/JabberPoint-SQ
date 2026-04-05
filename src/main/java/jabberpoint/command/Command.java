package jabberpoint.command;

// ISP: One method interface - classes only implement what they need
// OCP: New actions can be added by creating new Command classes.
// No need to modify MenuController or KeyController.
// LSP: All concrete commands (NextSlide, PrevSlide, etc.) can substitute
// Command anywhere. Controllers call execute() without knowing the type.
public interface Command {
    void execute();
}