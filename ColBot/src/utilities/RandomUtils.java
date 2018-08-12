package utilities;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RandomUtils {
	/**
	 * Converts an int array representing a rectangle to a Rectangle instance
	 * @param rect
	 */
	public static Rectangle toRectangle(int[] rect) {
		Rectangle result = new Rectangle(rect[0], rect[1], rect[2], rect[3]);
		return result;
	}
	
	/**
	 * Adds image specified by imgPath (inside resources/icons/gui/) to the specified button
	 * @param button
	 * @param imgPath
	 */
	public static void addImageToButton(JButton button, String imgName) {
		String imgPath = "resources/icons/gui/" + imgName;
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			System.err.println("[ImageUtilities.getImageAsIcon]: Failed to get image as an icon! Path: " + imgPath);
			e.printStackTrace();
		}
		
		Dimension size = button.getSize();
		Insets insets = button.getInsets();
		size.width -= insets.left + insets.right;
		size.height -= insets.top + insets.bottom;
		
		if (size.width > size.height) {
			size.width = -1;
		} else {
			size.height = -1;
		}
		
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		
		Image scaled = img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(scaled));
	}
	
	/**
	 * Adds a button to a jpanel and stretches it across the whole panel
	 * @param b
	 * @param p
	 */
	public static void addButtonToPanel(JButton b, JPanel p) {
		p.setBorder(BorderFactory.createEmptyBorder());
		Dimension panelSize = p.getSize();		
		b.setPreferredSize(panelSize);
		p.add(b);
	}
	
	/**
	 * Creates a JLabel with the specified image (in resources/icons/gui/)
	 * @param imageName
	 * @return
	 * @throws IOException 
	 */
	public static JLabel createImageLabel(String imageName) throws IOException {
		String imgPath = "resources/icons/gui/" + imageName;
		Image img = ImageIO.read(new File(imgPath));
		return new JLabel(new ImageIcon(img));
	}

}
