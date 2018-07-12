package player;

import system.GetSystemWindowLocation;
import system.GetSystemWindowLocation.GetWindowRectException;
import system.GetSystemWindowLocation.WindowNotFoundException;
import system.SetSystemWindowLocation;
import system.SystemWindow;

/**
 * This class is responsible for providing functionality regarding the window that is currently being listened to
 * @author Aljoša
 *
 */
public class WindowManager {
	private static SystemWindow swindow;
	
	public static void updateWindowLocation() {
		int[] newSystemWindowLocation = null;
		try {
			newSystemWindowLocation = GetSystemWindowLocation.getWindowLocation(swindow.getName());
		} catch (WindowNotFoundException e) {
			e.printStackTrace();
		} catch (GetWindowRectException e) {
			e.printStackTrace();
		}
		
		if(!(newSystemWindowLocation == null)) {
			swindow.setLocation(newSystemWindowLocation);
		} else {
			System.err.println("[Processor.updateWindowLocation]: newSystemWindowLocation is null!");
		}
	}
	
	/**
	 * This method moves the window by positioning it at coordinates specified by x and y
	 * @param x The new x coordinate of the upper-left corner of the window
	 * @param y The new y coordinate of the upper-left corner of the window
	 */

	public static void setWindowLocation(int x, int y) {
		try {
			SetSystemWindowLocation.setSystemWindowLocation(swindow.getName(), x, y, swindow.getLocation()[2], swindow.getLocation()[3]);
		} catch (WindowNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static SystemWindow getWindow() {
		return swindow;
	}
	
	public static void setWindow(SystemWindow window) {
		swindow = window;
	}

}
