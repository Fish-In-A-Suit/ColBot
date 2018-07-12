package system;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is responsible for taking screenshots
 * @author Aljoša
 *
 */
public class Screenshotter {
	private Robot robot;
	private BufferedImage screenshot;
	private String screenshotOutPath = "resources/images/screenshot.png";
	
	public Screenshotter() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.err.println("[Screenshotter.Screenshotter]: Can't create robot!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes a screenshot by the specified rectangle of a window
	 * @param rect A rectangle instance, which specified an area of the window
	 */
	public BufferedImage takeScreenshot(Rectangle rect) {
		//System.out.println("Taking screenshot of: [" + rect.getWidth() + " " + rect.getHeight() + " " + rect.getX() + " " + rect.getY() + "]");
		screenshot = robot.createScreenCapture(rect);
		return screenshot;
	}
	
	public BufferedImage getScreenshot() {
		return screenshot;
	}
	
	public void saveImage() {
		File scOutFile = new File(screenshotOutPath);
		
		//if it doesn't exist, then write to, else delete existing and write to new
		if(!scOutFile.exists()) {
			try {
				ImageIO.write((RenderedImage)screenshot, "png", new File(screenshotOutPath));
			} catch (IOException e) {
				System.err.println("Error saving a screenshot to " + screenshotOutPath);
				e.printStackTrace();
			}
		} else {
			scOutFile.delete();
			File newFile = new File(screenshotOutPath);
			try {
				ImageIO.write((RenderedImage)screenshot, "png", newFile);
			} catch (IOException e) {
				System.err.println("Error saving a screenshot to " + screenshotOutPath);
				e.printStackTrace();
			}
		}
	}

}
