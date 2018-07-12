package observer;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

import file.FileManager;

public class GlobalLoggerManager {
	private GlobalKeyboardListener kListener;
	private GlobalMouseListener mListener;
	private Logger nativeLogger;
	
	public GlobalLoggerManager() {
		kListener = new GlobalKeyboardListener();
		mListener = new GlobalMouseListener();
		
		addFiles();
		
		nativeLogger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		nativeLogger.setLevel(Level.OFF);
	}
	
	public void startLogging() {
		kListener.startKeyLogging();
		mListener.startMouseLogging();
	}
	
	public void cleanUp() {
		kListener.cleanUp();
		mListener.cleanUp();
	}
	
	private void addFiles() {
		FileManager.addFile(0, "fishing_key_log");
		FileManager.addFile(0, "fishing_mouse_log");
		
		FileManager.addFile(0, "test_key_log");
		FileManager.addFile(0, "test_mouse_log");
	}

}
