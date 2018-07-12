package system;

public class SystemProcess {
	private String name;
	private int PID;
	private String sessionName;
	private int session;
	private double memUsage;
	
	public SystemProcess(String name, int PID, String sessionName, int session, double memUsage) {
		//System.out.println("Creating a SProcess from " + name + " " + PID + " " + sessionName + " " + session + " " + memUsage);
		this.name = name;
		this.PID = PID;
		this.sessionName = sessionName;
		this.session = session;
		this.memUsage = memUsage;
	}

	public String getName() {
		return name;
	}
	
	public String getAsString() {
		return "name: " + name + " PID: " + PID + " sessionName " + sessionName + " sessionNum " + session + " memUsage: " + memUsage;
	}
}
