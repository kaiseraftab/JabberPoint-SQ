package jabberpoint.model;

import jabberpoint.strategy.DrawStrategy;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

// SRP: SlideItem only holds data, rendering is delegated to DrawStrategy
// Strategy pattern: draw() and getBoundingBox() delegate to strategy object
public abstract class SlideItem {
	private int level = 0;
	protected DrawStrategy drawStrategy;

	public SlideItem(int level, DrawStrategy strategy) {
		this.level = level;
		this.drawStrategy = strategy;
	}

	public SlideItem() {
		this(0, null);
	}

	public int getLevel() {
		return level;
	}

	// Strategy pattern: delegate drawing to strategy
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		return drawStrategy.getBoundingBox(g, observer, scale, style);
	}

	// Strategy pattern: delegate drawing to strategy
	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
		drawStrategy.draw(x, y, scale, g, style, observer);
	}
}