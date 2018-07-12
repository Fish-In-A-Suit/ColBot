package main;

import static org.lwjgl.glfw.GLFW.*;


import java.io.IOException;

import observer.GlobalLoggerManager;
import player.Player;
import player.Processor;
import player.WindowManager;
import renderEngine.Renderer;
import renderEngine.Window;
import system.GetSystemWindowLocation.GetWindowRectException;
import utilities.RandomUtils;
import system.Screenshotter;

public class ColBot implements Runnable {
	private boolean running = false;
	private Thread mainThread;
	
	private Window window = new Window();
	private Renderer renderer = new Renderer();
	private GlobalLoggerManager logger = new GlobalLoggerManager();
	private Processor processor = new Processor();
	private Player player = new Player();
	
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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void gameLoop() throws IOException {
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
				processInput();
				updateBuffers();
				updates++;
				delta--;
			}		
			renderer.render(window);
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				window.setTitle("ColBot | fps: " + frames + " ups: " + updates);
				updates = 0;
				frames = 0;
			}

			
			if(glfwWindowShouldClose(window.windowHandle)) {
				System.out.println("***** Starting cleanup operations *****");
				
				running = false;
				System.out.println("Stopping player...");
				player.stopPlaying();
				
				System.out.println("Stopping processor...");
				processor.stop();
				
				System.out.println("Stopping logger...");
				logger.cleanUp();
				
				System.out.println("Freeing callback and destroying window...");
				window.keycallback.free();
				glfwDestroyWindow(window.windowHandle);
				
				System.out.println("Terminating glfw context...");
				glfwTerminate();
				
				System.out.println("Exiting...");
				System.exit(0);
			}
		}
		
	}
	
	private void init() throws InterruptedException, IOException {
		try {
			processor.startProcessing("RuneScape.exe", "RuneScape");
		} catch (GetWindowRectException e) {
			e.printStackTrace();
		}
		logger.startLogging();
		window.init();
		try {
			renderer.init();
		} catch (Exception e) {
			System.err.println("[ColBot.init]: Failed to initialize renderer!");
			e.printStackTrace();
		}
		player.startPlaying();
	}
	
	private void processInput() throws IOException {
		glfwPollEvents();
	}
	
	private void updateBuffers() {
		glfwSwapBuffers(window.windowHandle);
	}
}
