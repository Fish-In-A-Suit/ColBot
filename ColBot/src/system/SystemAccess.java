package system;

import java.io.IOException;

/**
 * This class provides to low-level system functions and programs, for example executing commands from CMD
 * @author Aljoša
 *
 */
public class SystemAccess {
	public static void executeProgram(String directory, String programName) throws IOException {
		System.out.println("[SystemAccess.executeProgram]: Executing " + programName + " at " +  directory);
		Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", directory + "\\" + programName});
	}
	
	public static void stopProcess(String processName) throws IOException {
		Runtime.getRuntime().exec("TASKKILL /F /IM " + processName);
	}

}
