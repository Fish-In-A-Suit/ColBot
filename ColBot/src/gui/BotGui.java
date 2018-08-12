package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import net.miginfocom.layout.*;
import net.miginfocom.swing.MigLayout;
import utilities.ImageUtilities;
import utilities.RandomUtils;

import javax.swing.JPanel;

/**
 * This class provides functionality for building the base of the botting interface, ie the part of the interface that
 * all of the botting scripts share in common.
 * @author Uporabnik
 *
 */
public class BotGui {
	String name; //so it can be identified
	
	JPanel mainBotPanel;
	JPanel wrapper;
	
	JPanel botPanel;
		JPanel runStopWrapper;
		JPanel settings;
		JPanel other;
	
	JPanel displayPanel;
	
	/**
	 * Creates a new instance of BotGui with name as identifier. Do note that all that matters for this instance is it's mainBotPanel field,
	 * since this JPanel will be used by GuiManager to switch the panel which is connected to the content pane in order to switch between
	 * different user interfaces.
	 * @param name
	 * @param target
	 */
	public BotGui(String name) {
		System.out.println("[BotGui.BotGui]: Creating BotGui...");
		this.name = name;
		
		try {
			mainBotPanel = createBotUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the bot user interface (adds everything to a panel) and adds it to the target jrame
	 * @param target
	 * @throws IOException 
	 */
	private JPanel createBotUI() throws IOException {
		JPanel mainPanel = new JPanel(new MigLayout());
			wrapper = createWrapper(mainPanel);
				createBotPanel(wrapper);
				createDisplayPanel(wrapper);
		return mainPanel;
	}
	
	/**
	 * Creates the wrapper panel, the panel onto which bot and display panel are added
	 * @param target
	 * @return
	 */
	private JPanel createWrapper(JPanel target) {
		JPanel wrapper = new JPanel(new MigLayout());
		
		wrapper.setBorder(BorderFactory.createLineBorder(Color.green));
		wrapper.setPreferredSize(new Dimension(GuiManager.getMainFrame().getWidth(), GuiManager.getMainFrame().getHeight()));
		target.add(wrapper, "id wrapper");
		return wrapper;
	}
	
	/**
	 * Creates the left part of the botting ui, where the player can set various bot-related controls
	 * @param target
	 * @throws IOException 
	 */
	private void createBotPanel(JPanel target) throws IOException {
		botPanel = new JPanel(new MigLayout());
		botPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		// run/stop part of the bot panel
		runStopWrapper = new JPanel();
		runStopWrapper.setBorder(BorderFactory.createLineBorder(Color.yellow));
		botPanel.add(runStopWrapper, "north, gapbottom 15, gapright 10, w 100%, h 10%, id runStopWrapper");
		    
		    //JPanel which contains the running button
		    JPanel run = new JPanel(new BorderLayout());
		    run.setBorder(BorderFactory.createEmptyBorder());
		    run.setPreferredSize(new Dimension(50, 50));
		    runStopWrapper.add(run, "left, gapbottom 30, gapright 30, gapleft 30, gaptop 30, w 50%, h 50%");
		    //runStopWrapper.add(run, "left, pos 30 30 30 30");
		    
		    	JButton runButton = new JButton();
		    	runButton.setPreferredSize(new Dimension(50, 50));
		    	//ImageIcon runButtonIcon = ImageUtilities.getImageAsIcon("runButtonIcon.png");
		    	//runButton.setIcon(runButtonIcon);
		    	RandomUtils.addImageToButton(runButton, "runButtonIcon.png");
		    	run.add(runButton, BorderLayout.NORTH);
		    
		    JPanel stop = new JPanel();
		    stop.setBorder(BorderFactory.createLineBorder(Color.red));
		    stop.setPreferredSize(new Dimension(50, 50));
		    runStopWrapper.add(stop, "left, gapbottom 30, gapright 30, gapleft 30, gaptop 30, w 50%, h 50%");

		    	JButton stopButton = new JButton();
		    	stopButton.setPreferredSize(new Dimension(50, 50));
		    	RandomUtils.addImageToButton(stopButton, "stopButtonIcon.png");
		    	stop.add(stopButton);
		    
		// bot settings panel
		settings = new JPanel();
		settings.setBorder(BorderFactory.createLineBorder(Color.pink));
		botPanel.add(settings, "north, gapbottom 10, gapright 10, w 100%, h 60%");
		
		// "other" panel with no special use currently
		other = new JPanel();
		other.setBorder(BorderFactory.createLineBorder(Color.cyan));
		botPanel.add(other, "north, gapright 10, w 100%, h 25%");
		
		target.add(botPanel, "west, w 38.5%, h 100%");

	}
	
	/**
	 * Creates the right part of the botting ui, ie where the bot can be seen playing
	 * @param target
	 */
	private void createDisplayPanel(JPanel target) {
		//note: not really much used, since images are drawn over it
		displayPanel = new JPanel();
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		target.add(displayPanel, "w 61.5%, h 100%");
	}
	
	/**
	 * Sets the colour for main (back), botting (left) and display (right) panel
	 * @param r
	 * @param g
	 * @param b
	 */
	protected void setBackgroundColour(int r, int g, int b) {
		Color c = new Color(r, g, b);
		
		mainBotPanel.setBackground(c);
		botPanel.setBackground(c);
		displayPanel.setBackground(c);
	}
	
	/**
	 * Sets the colour of the "foreground panels" contained inside botpanel
	 * @param r
	 * @param g
	 * @param b
	 */
	protected void setForegroundColour(int r, int g, int b) {
		Color c = new Color(r, g, b);
		
		runStopWrapper.setBackground(c);
		settings.setBackground(c);
		other.setBackground(c);
	}

}
