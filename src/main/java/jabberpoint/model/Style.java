package jabberpoint.model;

import java.awt.Color;
import java.awt.Font;

// SRP: Style only holds presentation styling data
public class Style {
	private static Style[] styles;

	private static final String FONTNAME = "Helvetica";
	private int indent;
	private Color color;
	private Font font;
	private int fontSize;
	private int leading;

	public static void createStyles() {
		styles = new Style[5];
		styles[0] = new Style(0, Color.red, 48, 20);
		styles[1] = new Style(20, Color.blue, 40, 10);
		styles[2] = new Style(50, Color.black, 36, 10);
		styles[3] = new Style(70, Color.black, 30, 10);
		styles[4] = new Style(90, Color.black, 24, 10);
	}

	public int getIndent() {
		return indent;
	}

	public Color getColor() {
		return color;
	}

	public int getFontSize() {
		return fontSize;
	}

	public int getLeading() {
		return leading;
	}

	public static Style getStyle(int level) {
		if (level >= styles.length) {
			level = styles.length - 1;
		}
		return styles[level];
	}

	public Style(int indent, Color color, int points, int leading) {
		this.indent = indent;
		this.color = color;
		this.fontSize = points;
		font = new Font(FONTNAME, Font.BOLD, fontSize);
		this.leading = leading;
	}

	public String toString() {
		return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
	}

	public Font getFont(float scale) {
		return font.deriveFont(fontSize * scale);
	}
}