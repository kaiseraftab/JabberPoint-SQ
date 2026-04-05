package jabberpoint.model;

import jabberpoint.strategy.BitmapDrawStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// SRP: BitmapItem only holds image data, rendering delegated to BitmapDrawStrategy
public class BitmapItem extends SlideItem {
	private BufferedImage bufferedImage;
	private String imageName;

	protected static final String FILE = "File ";
	protected static final String NOTFOUND = " not found";

	public BitmapItem(int level, String name) {
		super(level, null);
		imageName = name;
		try {
			bufferedImage = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			System.err.println(FILE + imageName + NOTFOUND);
		}
		// Set strategy after image is loaded
		this.drawStrategy = new BitmapDrawStrategy(bufferedImage);
	}

	public BitmapItem() {
		this(0, null);
	}

	public String getName() {
		return imageName;
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}