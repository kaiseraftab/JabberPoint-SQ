package jabberpoint.view;

import jabberpoint.model.Presentation;
import jabberpoint.model.Slide;
import jabberpoint.model.Style;
import jabberpoint.observer.PresentationObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;

// DIP: Implements PresentationObserver interface, not tied to specific model
// SRP: Only responsible for rendering slides
public class SlideViewerComponent extends JComponent implements PresentationObserver {
	private Slide slide;
	private Font labelFont = null;
	private Presentation presentation = null;

	private static final long serialVersionUID = 227L;
	private static final Color BGCOLOR = Color.white;
	private static final Color COLOR = Color.black;
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final int XPOS = 1100;
	private static final int YPOS = 20;

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		setBackground(BGCOLOR);
		presentation = pres;
		labelFont = new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
		// Observer pattern: register this view as an observer
		presentation.addObserver(this);
	}

	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	// Observer pattern: called automatically when presentation changes
	@Override
	public void update(Presentation presentation, Slide slide) {
		this.presentation = presentation;
		this.slide = slide;
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.setColor(BGCOLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);
		if (presentation.getSlideNumber() < 0 || slide == null) {
			return;
		}
		g.setFont(labelFont);
		g.setColor(COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
				presentation.getSize(), XPOS, YPOS);
		Rectangle area = new Rectangle(0, YPOS, getWidth(), (getHeight() - YPOS));
		slide.draw(g, area, this);
	}
}