package scripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import system.Screenshotter;

public class Script {
	protected int id;
	protected Screenshotter scr;
	protected Robot robot;
	protected boolean savingEnabled;
	
	//each script has one int array of testing ranges for image processing
	protected int[] testingRanges;
	protected BufferedImage testingImage;
	
	public Script(int id) {
		this.id = id;
		scr = new Screenshotter();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			System.err.println("[Script.Script]: Failed to create robot!");
			e.printStackTrace();
		}
		
		testingRanges = new int[] {0,0,0,0,0,0};
		savingEnabled = false;
	}
	
	public void setSaving(boolean saving) {
		savingEnabled = saving;
	}

}
