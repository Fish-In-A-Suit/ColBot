package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import utilities.StringUtils;

/**
 * This class holds a reference to all system processes and provides a way to find them
 * @author Aljoša
 *
 */
public class ProcessFinder {
	static List<SystemProcess> currentProcesses = new ArrayList<>();
	private static int timesRun = 0; //to limit console printing to prevent spam
	
	/**
	 * This method returns a List of all running system processes, the data for each of them being
	 * stored as a SystemProcess instance
	 * @return
	 */
	public static List<SystemProcess> getCurrentProcesses() {
		try {
			String line;
			Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			while ((line = input.readLine()) != null) {
				if (timesRun < 1) {
					System.out.println(line);
				}
				
				String[] splitLine = line.split(" ");

				if (!splitLine[0].contains("=") && !splitLine[0].equals("Image") && !splitLine[0].equals("Memory") && !splitLine[0].equals("NVIDIA") && !splitLine[0].equals("System")) {		
					String[] refinedSplitLine = StringUtils.eliminateWhitespaceCharacters(splitLine);
					
					String name = null;
					int PID = 0;
					String sessionName = null;
					int sessionNum = 0;
					double memUsage = 0;
					
					int i = 0;
					for(String s:refinedSplitLine) {
						if (i == 0)  {
							name = s;
						} else if (i == 1) {
							PID = Integer.parseInt(s);
						} else if (i == 2) {
							sessionName = s;
						} else if  (i == 3) {
							sessionNum = Integer.parseInt(s);
						} else if (i == 4) {
							memUsage = Double.parseDouble(s);
						}
						i++;
					}
					
					SystemProcess currentProcess = new SystemProcess(name, PID, sessionName, sessionNum, memUsage);
					currentProcesses.add(currentProcess);
				} 
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("There are " + currentProcesses.size() + " currently running processes.");
		timesRun++;
		return currentProcesses;
	}
	
	/**
	 * This method searches currentProcesses to find a process which matches in name with the parameter. Note that getCurrentProcesses()
	 * method MUST be called prior to this method.
	 * @param name The name of the process to find
	 * @return
	 */
	public static SystemProcess findProcessByName(String name) {
		if (currentProcesses != null && currentProcesses.size() != 0) {
			System.out.println("Starting to search for process " + name + ".");
			
			if (timesRun < 1) {
				System.out.print("These are the names of currently running processes:");
				
				for (int i = 0; i < currentProcesses.size(); i++) {
				    String processName = currentProcesses.get(i).getName();
				    System.out.println(processName);
				}
				
			}
			
			for (int i = 0; i < currentProcesses.size(); i++) {
			    if (name.equals(currentProcesses.get(i).getName())) {
			    	System.out.println("Process " + name + " was found!");
			    	return currentProcesses.get(i);
			    }
			}
			
			System.out.println("No process by the name of " + name + " was found.");
			return null;
		} else {
			System.out.println("[ProcessFinder.findProcessByName]: Currently running processes haven't been yet queried! Make sure to do that first. Returning null.");
			return null;
		}

	}

}
