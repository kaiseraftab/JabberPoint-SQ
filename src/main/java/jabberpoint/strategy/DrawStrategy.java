package jabberpoint.strategy;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import jabberpoint.model.Style;

// ISP: Focused interface with only drawing-related methods
// Strategy pattern: defines the algorithm interface for rendering
public interface DrawStrategy {
    void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
    Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);
}