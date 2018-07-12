package utilities;

import java.awt.Rectangle;

public class RandomUtils {
	/**
	 * Converts an int array representing a rectangle to a Rectangle instance
	 * @param rect
	 */
	public static Rectangle toRectangle(int[] rect) {
		Rectangle result = new Rectangle(rect[0], rect[1], rect[2], rect[3]);
		return result;
	}

}
