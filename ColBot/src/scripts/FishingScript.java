package scripts;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JSlider;

import gui.GuiManager;
import main.Settings;
import player.WindowManager;
import utilities.ArrayUtilities;
import utilities.ImageUtilities;
import utilities.RandomUtils;

/**
 * This class holds information needed to perform the fishing activity
 * @author Aljoša
 *
 */
public class FishingScript extends Script{
	
	public FishingScript(int id) {
		super(id);
	}
	
	private int[] river = new int[] {106, 170, 116, 172, 105, 187};
	private int[] bubbles = new int[] {60, 169, 73, 203, 149, 208};
	//private int[] bubbles = new int[] {60, 169, 73, 203, 149, 182};
	
	private BufferedImage source;
	/*
	public void startFishing() {
		//System.out.println("Fishing!");
		source = scr.takeScreenshot(RandomUtils.toRectangle(WindowManager.getWindow().getLocation()));
		int mfWidth = GuiManager.getMainFrame().getWidth();
		int mfHeight = GuiManager.getMainFrame().getHeight();
		
		GuiManager.paint(GuiManager.getMainFrame(), source,
				mfWidth/3+55, 5, mfWidth-5, mfHeight/2-3, 0, 0, source.getWidth(), source.getHeight());
		
		//BufferedImage riverImgProcessed = ImageUtilities.eliminatePixels(source, river);
		//BufferedImage bubblesImgProcessed = ImageUtilities.eliminatePixels(source, bubbles);
		
		
		BufferedImage riverImgProcessed = ImageUtilities.eliminatePixels(source, GuiManager.getPixelRange());
		GuiManager.paint(GuiManager.getMainFrame(), riverImgProcessed,
				mfWidth/3+55, mfHeight/2+3, mfWidth-5, mfHeight-5, 0, 0, riverImgProcessed.getWidth(), riverImgProcessed.getHeight());
		
		if (Settings.savingEnabled) {
			//System.out.println("Saving!");
			ImageUtilities.saveImage(riverImgProcessed, "png", "/fishing/river.png");
			//ImageUtilities.saveImage(bubblesImgProcessed, "png", "/fishing/bubbles.png");
		}
	}
	*/

}
