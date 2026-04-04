package jabberpoint.factory;

import jabberpoint.model.BitmapItem;
import jabberpoint.model.SlideItem;
import jabberpoint.model.TextItem;

// Factory Method pattern: centralises SlideItem creation
// OCP: To add a new item type (e.g. VideoItem), only modify this class
public class ConcreteSlideItemFactory implements SlideItemFactory {
    private static final String TEXT = "text";
    private static final String IMAGE = "image";

    @Override
    public SlideItem createSlideItem(String kind, int level, String value) {
        if (TEXT.equals(kind)) {
            return new TextItem(level, value);
        } else if (IMAGE.equals(kind)) {
            return new BitmapItem(level, value);
        }
        return null;
    }
}