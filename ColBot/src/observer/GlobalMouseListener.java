package observer;

import java.io.File;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;

import file.FileManager;
import file.FileWriter;
import player.Processor;
import player.WindowManager;
import system.GetSystemWindowLocation;
import system.GetSystemWindowLocation.GetWindowRectException;
import system.GetSystemWindowLocation.WindowNotFoundException;
import utilities.ArrayUtilities;

public class GlobalMouseListener implements NativeMouseInputListener {
	File currentFile;
	private static double currentEventTime = 0;
	private static double lastEventTime = 0;
	private static int numEvents = 0;
	private static double delta = 0;	
	
	public void startMouseLogging() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(-1);
		}
		
		GlobalMouseListener mouseListener = new GlobalMouseListener();
		GlobalScreen.addNativeMouseListener((NativeMouseListener) mouseListener);
		GlobalScreen.addNativeMouseMotionListener((NativeMouseMotionListener) mouseListener);
	}
	
	public void nativeMouseClicked(NativeMouseEvent e) {
		String buttonRepresentation = null;
		
		lastEventTime = System.currentTimeMillis();
		delta = lastEventTime - currentEventTime;
		currentEventTime = lastEventTime;
		numEvents++;
		
		System.out.println("Mouse Clicked: " + e.getClickCount());
		
		//FileManager.setCurrentFile(FileManager.getKeyLoggingFiles().get("fishing_mouse_log"));
		FileManager.setCurrentFile(FileManager.getKeyLoggingFiles().get("test_mouse_log"));
		currentFile = FileManager.getCurrentFile();
		
		if(e.getButton() == 1) {
			buttonRepresentation = "LMB";
		} else if (e.getButton() == 2) {
			buttonRepresentation = "RMB";
		} else if (e.getButton() == 3) {
			buttonRepresentation = "MMB";
		}
		
		if(GlobalKeyboardListener.isActivated) {
			FileWriter.writeToFileMouse(currentFile, buttonRepresentation, currentEventTime, numEvents, delta);
		}
		
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		System.out.println("Mouse Pressed: " + e.getButton());
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
		System.out.println("Mouse Released: " + e.getButton());
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		//System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		//checks the window location if mouse is dragged
		try {
			int[] newLocation = GetSystemWindowLocation.getWindowLocation(WindowManager.getWindow().getName());
			WindowManager.getWindow().setLocation(newLocation);
			ArrayUtilities.displayArray(newLocation);
		} catch (WindowNotFoundException e1) {
			e1.printStackTrace();
		} catch (GetWindowRectException e1) {
			e1.printStackTrace();
		}
	}
	
	public void cleanUp() {
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
	}

}
