package observer;

import java.io.File;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import file.FileManager;
import file.FileWriter;

public class GlobalKeyboardListener implements NativeKeyListener  {
	File currentFile;
	
	private static double currentEventTime = 0;
	private static double lastEventTime = 0;
	private static double delta = 0;
	private static int numEvents = 0;

	static boolean isActivated = false;
	
	public void startKeyLogging() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.err.println("[GlobalKeyboardListener.startKeyLogging]: There was a probèem registering the native hook...");
		    System.err.println(e.getMessage());
		    System.exit(-1);
		}
		
		GlobalScreen.addNativeKeyListener(new GlobalKeyboardListener());
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		int keyCode = e.getKeyCode();
		String keyCodeAsText = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		lastEventTime = System.currentTimeMillis();
		delta = lastEventTime - currentEventTime;
		currentEventTime = lastEventTime;
		numEvents++;
		
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getKeyCode() == NativeKeyEvent.VC_F1) {
			if (isActivated == false) {
				isActivated = true;
				System.out.println("[GlobalKeyboardListener.nativeKeyPressed]: Logging is activated!");
			} else {
				isActivated = false;
				System.out.println("[GlobalKeyboardListener.nativeKeyPressed]: Logging is deactivated!");
			}
		}
		
		if (isActivated) {
			//find the current activity here and set the file (string path) based on current activity -- enums, switch?
			//currentFile = FileManager.getKeyLoggingFiles().get("fishing_key_log");
			currentFile = FileManager.getKeyLoggingFiles().get("test_key_log");
			FileManager.setCurrentFile(currentFile);
			FileWriter.writeToFileKeyboard(FileManager.getCurrentFile(), keyCode, keyCodeAsText, currentEventTime, numEvents, delta);
		}
		
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));		
	}
	
	public void cleanUp() {
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
	}

}
