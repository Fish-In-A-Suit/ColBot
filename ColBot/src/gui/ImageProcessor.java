package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import player.WindowManager;
import system.Screenshotter;
import utilities.ImageUtilities;
import utilities.RandomUtils;

public class ImageProcessor {
	public static void startImageProcessing() {
		Screenshotter scr = new Screenshotter();
		BufferedImage source = scr.takeScreenshot(RandomUtils.toRectangle(WindowManager.getWindow().getLocation()));
		
		
		//mainframe width and height
		int mfWidth = GuiManager.getMainFrame().getWidth();
		int mfHeight = GuiManager.getMainFrame().getHeight();
		
		//paints the source image in the top-right part of the img panel
		ImageProcessingGui.paint(GuiManager.getMainFrame(), source,
				mfWidth/3+60, 60, mfWidth-15, mfHeight/2-3, 0, 0, source.getWidth(), source.getHeight());
		
		BufferedImage processedImg = ImageUtilities.eliminatePixels(source, ImageProcessingGui.getPixelRange());
		
		//paints the processed image in the bottom-right part of the img panel
		ImageProcessingGui.paint(GuiManager.getMainFrame(), processedImg,
				mfWidth/3+60, mfHeight/2+3, mfWidth-15, mfHeight-15, 0, 0, processedImg.getWidth(), processedImg.getHeight());
				
		
		/* try2
		//THIS SEEMS TO BE INCORRECT ASWELL!
		//image panel width and height
		int IPWidth = ImageProcessingGui.getImgPanel().getWidth();
		int IPHeight = ImageProcessingGui.getImgPanel().getHeight();
		
		//FIX THIS THE COVNERSION BETWEEN COORDINATE SYSTEMS IS INCORRECT!
		//image panel upper-left corner converted to the frame coordinate system
		Point IPLoc = SwingUtilities.convertPoint(ImageProcessingGui.getImgPanel(), ImageProcessingGui.getImgPanel().getLocation(), GuiManager.getMainFrame());
		int IPLocX = IPLoc.x;
		int IPLocY = IPLoc.y;
		
		System.out.println("Location of x: " + IPLocX);
		System.out.println("Location of y: " + IPLocY);
		
		ImageProcessingGui.paint(GuiManager.getMainFrame(), source, IPLocX, IPLocY, IPWidth, IPHeight, 0, 0, source.getWidth(), source.getHeight());
		*/
		
		/*try3
		Point IPLocRelative = ImageProcessingGui.getImgPanel().getLocation();
		Component currentComponent = ImageProcessingGui.getImgPanel();
		
		while (currentComponent != null && currentComponent != ImageProcessingGui.getMainPanel()) {
			Point relativeLocation = currentComponent.getLocation();
			IPLocRelative.translate(relativeLocation.x, relativeLocation.y);
			currentComponent = currentComponent.getParent();
		}
		//it's translated now
		Point IPLoc = IPLocRelative;
		
		int IPLocX = IPLoc.x;
		int IPLocY = IPLoc.y;
		
		int IPWidth = ImageProcessingGui.getImgPanel().getWidth();
		int IPHeight = ImageProcessingGui.getImgPanel().getHeight();
		
		ImageProcessingGui.paint(GuiManager.getMainFrame(), source, IPLocX, IPLocY, IPWidth, IPHeight, 0, 0, source.getWidth(), source.getHeight());
		*/
	}	
}
