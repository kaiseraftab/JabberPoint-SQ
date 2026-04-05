package jabberpoint.model;

import jabberpoint.strategy.TextDrawStrategy;

// SRP: TextItem only holds text data, rendering delegated to TextDrawStrategy
public class TextItem extends SlideItem {
	private String text;

	private static final String EMPTYTEXT = "No Text Given";

	public TextItem(int level, String string) {
		super(level, new TextDrawStrategy(string));
		text = string;
	}

	public TextItem() {
		this(0, EMPTYTEXT);
	}

	public String getText() {
		return text == null ? "" : text;
	}

	public String toString() {
		return "TextItem[" + getLevel() + "," + getText() + "]";
	}
}