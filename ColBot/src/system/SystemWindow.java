package system;

/**
 * This class represents a non-java window (such as Chrome for example). Each window has a name and a location
 * @author Aljoša
 *
 */
public class SystemWindow {
	private String name;
	private int[] location;
	
	public SystemWindow(String name, int[] location) {
		this.name = name;
		this.location = location;
	}
	
	public void setLocation(int[] location) {
		this.location = location;
	}
	
	public int[] getLocation() {
		return location;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
