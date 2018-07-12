package scripts;

import java.awt.image.BufferedImage;

import player.WindowManager;
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
	
	private BufferedImage riverImg;
	private BufferedImage bubblesImg;
	
	public void startFishing() {
		System.out.println("Fishing!");
		riverImg = scr.takeScreenshot(RandomUtils.toRectangle(WindowManager.getWindow().getLocation()));
		bubblesImg = scr.takeScreenshot(RandomUtils.toRectangle(WindowManager.getWindow().getLocation()));
		
		BufferedImage riverImgProcessed = ImageUtilities.eliminatePixels(riverImg, river);
		BufferedImage bubblesImgProcessed = ImageUtilities.eliminatePixels(bubblesImg, bubbles);
		
		if (savingEnabled) {
			System.out.println("Saving!");
			ImageUtilities.saveImage(riverImgProcessed, "png", "/fishing/river.png");
			ImageUtilities.saveImage(bubblesImgProcessed, "png", "/fishing/bubbles.png");
		}
	}

}
