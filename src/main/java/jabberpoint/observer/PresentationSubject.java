package jabberpoint.observer;

// ISP: Separate interface for the Subject role
public interface PresentationSubject {
    void addObserver(PresentationObserver observer);
    void removeObserver(PresentationObserver observer);
    void notifyObservers();
}