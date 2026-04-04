package jabberpoint.factory;

import jabberpoint.model.SlideItem;

// OCP: New item types can be added without modifying XMLAccessor
// DIP: XMLAccessor depends on this interface, not concrete item classes
public interface SlideItemFactory {
    SlideItem createSlideItem(String kind, int level, String value);
}