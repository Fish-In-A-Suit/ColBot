package system;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import system.GetSystemWindowLocation.WindowNotFoundException;

/**
 * This class is responsible for providing functionality for setting a system window
 * @author Aljoša
 *
 */
public class SetSystemWindowLocation {
	  public interface User32 extends StdCallLibrary {
	      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class,
	               W32APIOptions.DEFAULT_OPTIONS);

	      HWND FindWindow(String lpClassName, String lpWindowName);

	      boolean SetWindowPos(HWND handle, HWND hwndInsertAfter, int x, int y, int cx, int cy, int uFlags);
	   }
	  
	  /**
	   * This methods positions the window to a new location
	   * @param windowName
	   * @param x The new position of the left side of the window
	   * @param y The new position of the top of the window
	   * @param cx The new width of the window in pixels
	   * @param cy The new height of the window in pixels
	   * @throws WindowNotFoundException 
	   */
	  public static void setSystemWindowLocation(String windowName, int x, int y, int cx, int cy) throws WindowNotFoundException {
		  //System.out.println("[SetSystemWindowLocation.setSystemWindowLocation]: Setting window location to " + x + " " + y + ", width: " + cx + " height: " + cy);
		  HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
	      if (hwnd == null) {
	         throw new WindowNotFoundException("", windowName);
	      }
	      
	      boolean result = User32.INSTANCE.SetWindowPos(hwnd, null, x, y, cx, cy, 0);
	      if (result == false) {
	    	  System.err.println("[SetSystemWindowLocation.setSystemWindowLocation]: Failed to set window location for window " + windowName);
	      }
	  }
	  
	  

}
