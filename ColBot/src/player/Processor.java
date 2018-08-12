package player;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import system.GetSystemWindowLocation;
import system.GetSystemWindowLocation.GetWindowRectException;
import system.GetSystemWindowLocation.WindowNotFoundException;
import system.ProcessFinder;
import system.SetSystemWindowLocation;
import system.SystemAccess;
import system.SystemProcess;
import system.SystemWindow;
import utilities.ArrayUtilities;

/**
 * This class tracks whether a certain process (application) is running and executes it if not.
 * It uses ProcessFinder class to search for processes. If the process sought after hasn't been found (ie isn't running), then it
 * executes it and stores it as newProcess. Otherwise (if a process is already running) it stores this process as oldProcess.
 * @author Aljoša
 *
 */
public class Processor {
	private String programDir; //get this from the process in the future
	private String launcherName; //get this from the process in the future
	
	private SystemProcess process;
	/**
	 * Starts processing the process found by processName (if it exists)
	 * @param processName
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void startProcessing(String processName, String windowName) throws IOException, InterruptedException, GetSystemWindowLocation.GetWindowRectException {
		//launches the program
		System.out.println("[Processor.startProcessing]: Finding " + processName + " process...");
		
		ProcessFinder.getCurrentProcesses();
		process = ProcessFinder.findProcessByName(processName);
		
		programDir = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Jagex";
		launcherName = "RuneScape Launcher.url";
		
		String programDir1 = "D:\\Aljoša\\runescape\\RuneScape Launcher";
		String launcherName1 = "RuneScape.exe";
		
		if (process == null) {
			System.out.println("[Processor.startProcessing]:" + processName + " hasn't been found...");
			SystemAccess.executeProgram(programDir, launcherName);
			
			//make it based when runescape finishes loading in the future
			TimeUnit.SECONDS.sleep(1); //if less than loading time of runescape globalmouselistener will throw nullpointer
			
			ProcessFinder.getCurrentProcesses();
			process = ProcessFinder.findProcessByName(processName);
		} else {
			System.out.println("[Processor.startProcessing]: " + processName + " is already running!");			
		}
		
		//finds system window and its location and sets the window (to which it is listened to) to the WindowManager
		int[] systemWindowLocation = null;
		try {
			System.out.println("[Processor.startProcessing]: Finding the location of " + windowName + " window.");
			systemWindowLocation = GetSystemWindowLocation.getWindowLocation(windowName);
		} catch (WindowNotFoundException e) {
			e.printStackTrace();
		}
		
		if (windowName != null && systemWindowLocation != null) {
			WindowManager.setWindow(new SystemWindow(windowName, systemWindowLocation));
		} else {
			System.err.println("[Processor.startProcessing]: Cannot create a SystemWindow from " + windowName);
		}
		System.out.printf("The corner locations for the window \"%s\" are %s",  windowName, Arrays.toString(systemWindowLocation));	
	}
	
	public void stop() throws IOException {	
			System.out.println("[Processor.stop]: Stopping process " + process.getName());
			SystemAccess.stopProcess(process.getName());
	}
}
