package jabberpoint.strategy;

import jabberpoint.model.Style;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

// SRP: Only handles bitmap/image rendering — no data storage
// Strategy pattern: concrete strategy for drawing bitmap items
public class BitmapDrawStrategy implements DrawStrategy {
    private BufferedImage bufferedImage;

    public BitmapDrawStrategy(BufferedImage image) {
        this.bufferedImage = image;
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) {
        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);
        g.drawImage(bufferedImage, width, height,
                (int) (bufferedImage.getWidth(observer) * scale),
                (int) (bufferedImage.getHeight(observer) * scale), observer);
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) {
        return new Rectangle((int) (myStyle.indent * scale), 0,
                (int) (bufferedImage.getWidth(observer) * scale),
                ((int) (myStyle.leading * scale)) +
                        (int) (bufferedImage.getHeight(observer) * scale));
    }
}