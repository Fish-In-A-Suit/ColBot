package player;

import java.awt.image.BufferedImage;
import java.io.File;

import scripts.FishingScript;
import scripts.Script;
import system.Screenshotter;
import system.SystemWindow;
import utilities.ImageUtilities;
import utilities.RandomUtils;

/**
 * This class simulates a live player of the game
 * @author Aljoša
 *
 */
public class Player implements Runnable {
	private Thread playerThread;
	private boolean running;

	private Screenshotter screenshotter; 
	private BufferedImage currentImage;
	private FishingScript fishingScript;
	
	public Player() {
		playerThread = new Thread(this, "playerThread");
		screenshotter = new Screenshotter(); 
		fishingScript = new FishingScript(1);
	}
	
	public void startPlaying() {
		running = true;
		fishingScript.setSaving(true);
		playerThread.start();
	}

	@Override
	public void run() {	
		while(running = true) {
			//gets the current window and sets location to 0 0 if needed
			SystemWindow currentWindow = WindowManager.getWindow();
			if(currentWindow.getLocation()[0] != 0 && currentWindow.getLocation()[1] != 0) {
				WindowManager.setWindowLocation(0, 0);
			}
			fishingScript.startFishing();
		}
	}
	
	public void stopPlaying() {
		running = false;
	}
}
