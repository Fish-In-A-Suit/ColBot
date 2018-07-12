package file;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is responsible for writing to files
 * @author Aljoša
 *
 */

public class FileWriter {
	
	/**
	 * This method writes keyboard log to the specified file
	 * @param file The file to write to
	 * @param keyCode Numerical representation of the key pressed
	 * @param keyCodeAsChar Letter representation of the key pressed
	 * @param currentTime The time of the event in currentTimeMillis
	 * @param numEvent The number of events since the start of running the application
	 * @param delta Difference in time between this and the previous key press
	 */
	public static void writeToFileKeyboard(File file, int keyCode, String keyCodeAsText, double currentTime, int numEvent, double delta) {	
		if (delta > 1.529792670253E12) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			FileUtilities.writeToFile(file, "! Starting new logging session. Time: " + dateFormat.format(date));
		} else {
			FileUtilities.writeToFile(file, ("k " + keyCode + " " + keyCodeAsText + " " + currentTime + " " + numEvent + " " + delta));
		}		
	}
	
	/**
	 * This method writes mouse log to the specified file
	 * @param file The file to write to
	 * @param buttonAsString Letter representation of the key pressed
	 * @param currentTime Current time of the event
	 * @param numEvent Number of events since the start of application
	 * @param delta Difference in time between this and the previous key press
	 */
	public static void writeToFileMouse(File file, String buttonAsString, double currentTime, int numEvent, double delta) {
		if (delta > 1.529792670253E12) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			FileUtilities.writeToFile(file, ("! Starting new logging session. Time: " + dateFormat.format(date)));
		} else {
			FileUtilities.writeToFile(file, ("m " + buttonAsString + " " + currentTime + " " + numEvent + " " + delta));
		}
	}
}
