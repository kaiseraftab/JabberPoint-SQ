package jabberpoint.observer;

// ISP: Separate interface for the Subject role
// OCP: New views can observe the presentation without modifying
// Presentation. Just implement PresentationObserver and register.
public interface PresentationSubject {
    void addObserver(PresentationObserver observer);
    void removeObserver(PresentationObserver observer);
    void notifyObservers();
}