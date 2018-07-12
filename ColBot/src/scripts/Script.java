package scripts;

import java.awt.AWTException;
import java.awt.Robot;

import system.Screenshotter;

public class Script {
	protected int id;
	protected Screenshotter scr;
	protected Robot robot;
	protected boolean savingEnabled;
	
	public Script(int id) {
		this.id = id;
		scr = new Screenshotter();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.err.println("[Script.Script]: Failed to create robot!");
			e.printStackTrace();
		}
		
		savingEnabled = false;
	}
	
	public void setSaving(boolean saving) {
		savingEnabled = saving;
	}

}
