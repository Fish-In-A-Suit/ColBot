package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class provides various functionality regarding images, such as saving them on disk, reading them, etc
 * @author Aljoša
 *
 */
public class ImageUtilities {
	static int numRuns = 0;
	
	public static void saveImage(BufferedImage img,  String formatName, String output) {
		System.out.println("Saving image " + img.toString() + " to " + output);
		String outputPath = "resources/images/"+output;
		File outputFile = new File(outputPath);

		try {
			ImageIO.write(img, formatName, outputFile);
		} catch (IOException e) {
			System.err.println("Error writing image to file " + outputFile.getAbsolutePath());
			e.printStackTrace();
		}
	
	}
	
	public static void displayData(BufferedImage img) {
		System.out.println("[ImageUtilities.displayData]: Displaying image data: " + img.getColorModel().toString());
	}
	
	/**
	 * This methods eliminates all of the pixels in the image that aren't located within the range specified
	 * by int array range. Note that this array MUST be of length 6 (so indexes 0 through 5). Elements at indexes
	 * 0 and 1 refer to the red range, elements at indexes 2 and 3 refer to the green range and elements at indexes
	 * 4 and 5 refer to the blue range.
	 * @param img BufferedImage whose pixels to eliminate
	 * @param range Red, green and blue range values of pixels which should not be eliminated
	 * @return Modified BufferedImage instance, with pixels out of the range set to transparent
	 */
	public static BufferedImage eliminatePixels(BufferedImage img, int[] range) {
		int width = img.getWidth();
		int height = img.getHeight();
		
		int redMin = 0;
		int redMax = 0;
		int greenMin = 0;
		int greenMax = 0;
		int blueMin = 0;
		int blueMax = 0;
		
		if(range.length == 6) {
			redMin = range[0];
			redMax = range[1];
			greenMin = range[2];
			greenMax = range[3];
			blueMin = range[4];
			blueMax = range[5];
		}
		
		for (int i = 0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				int pixel = img.getRGB(j, i);
				
				int alpha = (pixel>>24) & 0xff;
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = pixel & 0xff;
				
				if((red >= redMin && red <= redMax) &&
						(green >= greenMin && green <= greenMax) &&
						(blue >= blueMin && blue <= blueMax)) {
					} else {
						img.setRGB(j, i, 16777215);
			    }
			}
		}	
		return img;
	}
}
