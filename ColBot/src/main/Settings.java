package main;

import gui.GuiStates;

/**
 * This class holds various variable which determine how ColBot operates (ex, if images are
 * saved to device, etc)
 * @author Uporabnik
 *
 */
public class Settings {
	public static boolean savingEnabled = false;
	public static GuiStates currentGuiState = GuiStates.DEVELOPER; //default
	private static boolean guiStateChanged = false;
	private static boolean shouldRenderImg = true; //whether screenshot and processed screenshot should be rendered
	
	private Settings() {
		
	}
	
	public static void setSaving(boolean val) {
		savingEnabled = val;
		
		if (val == true) {
			System.out.println("[Settings.setSaving]: Saving is now enabled.");
		} else {
			System.out.println("[Settings.setSaving]: Saving is now disabled.");
		}
	}
	
	public static void setCurrentGuiState(GuiStates state) {
		System.out.println("Setting the current gui state to: " + state.toString());
		
		if (!(currentGuiState == state)) {
			guiStateChanged = true;
		}
		
		currentGuiState = state;
	}
	
	/**
	 * Returns the boolean which denotes whether the gui state has been changed
	 * @return
	 */
	public static boolean getGuiStateChanged() {
		return guiStateChanged;
	}
	
	public static void setGuiStateChanged(boolean val) {
		guiStateChanged = val;
	}
	
	public static boolean getShouldRender() {
		return shouldRenderImg;
	}
	
	/**
	 * Set whether the screenshot and processed screenshot should be rendered
	 * @param val
	 */
	public static void setShouldRender(boolean val) {
		shouldRenderImg = val;
	}

}
