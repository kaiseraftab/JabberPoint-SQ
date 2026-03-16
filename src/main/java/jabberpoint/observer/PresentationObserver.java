package jabberpoint.observer;

import jabberpoint.model.Presentation;
import jabberpoint.model.Slide;

// ISP: Small focused interface - only one method
public interface PresentationObserver {
    void update(Presentation presentation, Slide slide);
}