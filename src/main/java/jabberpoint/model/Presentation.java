package jabberpoint.model;

import jabberpoint.observer.PresentationObserver;
import jabberpoint.observer.PresentationSubject;
import java.util.ArrayList;
import java.util.List;

// SRP: Presentation only manages slide data and observer notification
// DIP: Depends on PresentationObserver interface, not concrete views
// OCP: New views can be added without modifying this class
public class Presentation implements PresentationSubject {
	private String showTitle;
	private ArrayList<Slide> showList = null;
	private int currentSlideNumber = 0;
	private List<PresentationObserver> observers = new ArrayList<>();

	public Presentation() {
		clear();
	}

	// Observer pattern: subscribe
	@Override
	public void addObserver(PresentationObserver observer) {
		observers.add(observer);
	}

	// Observer pattern: unsubscribe
	@Override
	public void removeObserver(PresentationObserver observer) {
		observers.remove(observer);
	}

	// Observer pattern: notify all registered observers
	@Override
	public void notifyObservers() {
		for (PresentationObserver observer : observers) {
			observer.update(this, getCurrentSlide());
		}
	}

	public int getSize() {
		return showList.size();
	}

	public String getTitle() {
		return showTitle;
	}

	public void setTitle(String nt) {
		showTitle = nt;
	}

	public int getSlideNumber() {
		return currentSlideNumber;
	}

	// When slide changes, notify all observers instead of calling one specific view
	public void setSlideNumber(int number) {
		currentSlideNumber = number;
		notifyObservers();
	}

	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setSlideNumber(currentSlideNumber - 1);
		}
	}

	public void nextSlide() {
		if (currentSlideNumber < (showList.size() - 1)) {
			setSlideNumber(currentSlideNumber + 1);
		}
	}

	public void clear() {
		showList = new ArrayList<Slide>();
		setSlideNumber(-1);
	}

	public void append(Slide slide) {
		showList.add(slide);
	}

	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()) {
			return null;
		}
		return showList.get(number);
	}

	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}
}