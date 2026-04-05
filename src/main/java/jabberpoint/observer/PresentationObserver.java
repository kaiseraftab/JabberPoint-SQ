package jabberpoint.observer;

import jabberpoint.model.Presentation;
import jabberpoint.model.Slide;

// ISP: Small focused interface - only one method
// Observers are not forced to implement unnecessary methods.
public interface PresentationObserver {
    void update(Presentation presentation, Slide slide);
}