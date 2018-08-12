package main;

import static org.lwjgl.glfw.GLFW.*;


import java.io.IOException;

import gui.GuiManager;
import gui.GuiStates;
import observer.GlobalLoggerManager;
import player.Player;
import player.Processor;
import player.WindowManager;
import system.GetSystemWindowLocation.GetWindowRectException;
import utilities.RandomUtils;
import system.Screenshotter;

public class ColBot implements Runnable {
	private boolean running = false;
	private Thread mainThread;

	private GlobalLoggerManager logger = new GlobalLoggerManager();
	private Processor processor = new Processor();
	private Player player = new Player();
	private GuiManager guiManager = new GuiManager();
	
	public static void main(String[] args) {
		new ColBot().start();
	}
	
	private void start() {
		System.out.println("Starting ColBot!");
		running = true;
		mainThread = new Thread( this, "mainThread");
		mainThread.start();
	}


	public void run() {
		try {
			init();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		try {
			gameLoop();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private void gameLoop() throws IOException, InterruptedException {
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1/60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		System.out.println("[ColBot.gameLoop]: Entering the game loop!");
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if (delta >= 1.0) {
				updates++;
				delta--;
			}		
			frames++;
			
			if (Settings.getGuiStateChanged() == true) {
				System.out.println("Changing gui!");
				guiManager.changeGUI();
				Settings.setGuiStateChanged(false);
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				//guiManager.drawGUI();
				timer += 1000;
				System.out.println("fps: " + frames + ", ups: " + updates);
				updates = 0;
				frames = 0;
			}
		}
		
	}
	
	private void init() throws InterruptedException, IOException {
		
		try {
			processor.startProcessing("RuneScape.exe", "RuneScape");
		} catch (GetWindowRectException e) {
			e.printStackTrace();
		}
		Thread.sleep(2);
		guiManager.createGUI();
		logger.startLogging();
		player.startPlaying();
	}
}
