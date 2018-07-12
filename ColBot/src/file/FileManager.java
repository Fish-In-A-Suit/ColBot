package file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This class has a list of all files and manages which file is currently connected so it can be logged into
 * @author Aljoša
 *
 */
public class FileManager {
	private static Map<String, File> keyloggingFiles;
	private static Map<String, File> loggingFiles;
	
	private static File currentFile; //this is the current file which is being written into
	
	
	/* won't work because keyloggingFiles and loggingFiles are static
	public FileManager() { 
		keyloggingFiles = new HashMap<>();
		loggingFiles = new HashMap<>();
	}*/
	
	//static/class initializer
	static {
		keyloggingFiles = new HashMap<>();
		loggingFiles = new HashMap<>();
	}
	
	/**
	 * This method adds a Fileinstance (which is created from a specified file name without the file extension)
	 * to the corresponding Map, with fileName as key. Keylogging file is a file which is used when the player is being keylogged, logging file is
	 * a file which is used to log the actions of the program playing.
	 * @param qualifier: 0 = keylogging file; 1 = logging file
	 * @param filePath The path to the file. Keylogging files should be stored as ACTIVITY_key_log, logging files should be stored as ACTIVITY_log (example: fishing_key_log)
	 */
	public static void addFile(int qualifier, String fileName){
		String filePath;
		
		if (qualifier == 0) {
			filePath = "resources/keylogging/" + fileName + ".txt";
			File file = new File(filePath);
			keyloggingFiles.put(fileName, file);
			
		} else if (qualifier == 1) {
			filePath = "resources/logging/" + fileName + ".txt";
			File file = new File(filePath);
			loggingFiles.put(fileName, file);
		}	
	}
	
	public static Map<String, File> getKeyLoggingFiles() {
		return keyloggingFiles;
	}
	
	public static Map<String, File> getLoggingFiles() {
		return loggingFiles;
	}
	
	public static File getCurrentFile() {
		return currentFile;
	}
	
	public static void setCurrentFile(File file) {
		currentFile = file;
	}

}

