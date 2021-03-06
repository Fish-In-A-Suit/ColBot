package player;

import java.awt.image.BufferedImage;
import java.io.File;

import gui.GuiStates;
import gui.ImageProcessor;
import main.Settings;
import scripts.FishingScript;
import scripts.Script;
import system.Screenshotter;
import system.SystemWindow;
import utilities.ImageUtilities;
import utilities.RandomUtils;

/**
 * This class simulates a live player of the game
 * @author Aljo�a
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
			/*
			if (Settings.currentGuiState == GuiStates.DEVELOPER) {
				System.out.println("[Player.run]: Processing image!");
				ImageProcessor.startImageProcessing();
			}*/
			boolean shouldRender = Settings.getShouldRender();
			System.out.print("" + Settings.getShouldRender()); //weird bug :/ if this is called, correct results, else incorrect... wtf
			if (shouldRender == true) {
				ImageProcessor.startImageProcessing();
			}
			//fishingScript.startFishing();
		}
	}
	
	public void stopPlaying() {
		running = false;
	}
}
