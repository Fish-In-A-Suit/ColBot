package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import main.Settings;

public class GuiManager implements ActionListener {
	
	private static JFrame mainFrame;
	
	//holds connection between each menu item and corresponding gui state
	private static Map<JMenuItem, GuiStates> itemToState = new HashMap<>();
	
	private BotGui botGui;
	private FishingGui fishingGui;
	
	/**
	 * Creates the default image processing gui
	 */
	public void createGUI() {
		mainFrame = new JFrame();
		mainFrame.setSize(1080, 720);
		
		loadGuis();
		
		createMenuBar(mainFrame);
		
		JPanel imageProcessingPanel = ImageProcessingGui.createImageProcessingGui();
		mainFrame.getContentPane().add(imageProcessingPanel);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	/**
	 * Changes the gui based on the current gui state (stored in Settings). 
	 */
	public void changeGUI() {
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().repaint();
		
		JPanel mainPanel = null;
		
		switch(Settings.currentGuiState) {
		case DEVELOPER:
			mainPanel = ImageProcessingGui.createImageProcessingGui();
			Settings.setShouldRender(true);
			break;
		case FISHING:
			//mainPanel = new BotGui("fishing").mainBotPanel;
			mainPanel = fishingGui.mainBotPanel;
			Settings.setShouldRender(true);
			break;
		}
		mainFrame.getContentPane().revalidate();
		
		if (null != mainPanel) {
			mainFrame.getContentPane().add(mainPanel);
		}
	}
	
	public static JFrame getMainFrame() {
		return mainFrame;
	}
	
	/**
	 * Creates the menu bar and appends it to target JFrame
	 * @param target
	 */
	private void createMenuBar(JFrame target) {
		JMenuBar menuBar;
		
		//create the menu bar
		menuBar = new JMenuBar();
		
		createMenus(menuBar);
		
		target.setJMenuBar(menuBar);
	}
	
	/**
	 * Creates the menus for different skills in the menu bar
	 * @param target
	 */
	private void createMenus(JMenuBar target) {
		JMenu menu;
		JMenuItem menuItem;
		
		menu = new JMenu("Developer");
			menuItem = new JMenuItem("Image processing", new ImageIcon("resources/icons/wrench.png"));
				itemToState.put(menuItem, GuiStates.DEVELOPER);
				menuItem.addActionListener(this);
				menuItem.getAccessibleContext().setAccessibleDescription("The developer perspective.");
		menu.add(menuItem);
		
		target.add(menu);
		
		//note: icon size 20x20
		
		/*
		 * Vsak menu item more bit povezan s svojim GUI. Nrdim enum za vse mozne razlicne GUI pa storam connection med tem menuItem in
		 * pripadajocim enumom. GuiSettings class drzi currently active gui enum, ki klice pripadajoc class ki narise gui -- switch statement?
		 * Ce user klikne drug menuItem pol se v GuiSettings spremeni currently active gui enum in s tem se narise nou gui (gui manager) --> premakn
		 * guimanager.creategui v render loop namest v init loop da se bo skos klical
		 */
		menu = new JMenu("Combat");
		    menuItem = new JMenuItem("Attack", new ImageIcon("resources/icons/attack.png"));
		    	itemToState.put(menuItem, GuiStates.ATTACK);
		    	menuItem.addActionListener(this);
		        menu.add(menuItem);
		    menuItem = new JMenuItem("Strength", new ImageIcon("resources/icons/strength.png"));
		    	itemToState.put(menuItem, GuiStates.STRENGTH);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Defence", new ImageIcon("resources/icons/defence.png"));
		    	itemToState.put(menuItem, GuiStates.DEFENCE);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Ranged", new ImageIcon("resources/icons/ranged.png"));
		    	itemToState.put(menuItem, GuiStates.RANGED);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Magic", new ImageIcon("resources/icons/magic.png"));
		    	itemToState.put(menuItem, GuiStates.MAGIC);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Prayer", new ImageIcon("resources/icons/prayer.png"));
		    	itemToState.put(menuItem, GuiStates.PRAYER);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Summoning", new ImageIcon("resources/icons/summoning.png"));
		    	itemToState.put(menuItem, GuiStates.SUMMONING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);	
		target.add(menu);
		
		menu = new JMenu("Gathering");
		     menuItem = new JMenuItem("Fishing", new ImageIcon("resources/icons/fishing.png"));
		     	itemToState.put(menuItem, GuiStates.FISHING);
		     	menuItem.addActionListener(this);
		     	menu.add(menuItem);
		     menuItem = new JMenuItem("Mining", new ImageIcon("resources/icons/mining.png"));
		     	itemToState.put(menuItem, GuiStates.MINING);
		     	menuItem.addActionListener(this);
		     	menu.add(menuItem);
		     menuItem = new JMenuItem("Woodcutting", new ImageIcon("resources/icons/woodcutting.png"));
		     	itemToState.put(menuItem, GuiStates.WOODCUTTING);
		     	menuItem.addActionListener(this);
		     	menu.add(menuItem);
		     menuItem = new JMenuItem("Farming", new ImageIcon("resources/icons/farming.png"));
		     	itemToState.put(menuItem, GuiStates.FARMING);
		     	menuItem.addActionListener(this);
		     	menu.add(menuItem);
		     menuItem = new JMenuItem("Hunter", new ImageIcon("resources/icons/hunter.png"));
		      	itemToState.put(menuItem, GuiStates.HUNTER);
		      	menuItem.addActionListener(this);
		     	menu.add(menuItem);
		     menuItem = new JMenuItem("Divination", new ImageIcon("resources/icons/divination.png"));
		     	itemToState.put(menuItem, GuiStates.DIVINATION);
		     	menuItem.addActionListener(this);
		     	menu.add(menuItem);	
		target.add(menu);
		
		menu = new JMenu("Artisan");
		    menuItem = new JMenuItem("Cooking", new ImageIcon("resources/icons/cooking.png"));
		    	itemToState.put(menuItem, GuiStates.COOKING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Smithing", new ImageIcon("resources/icons/smithing.png"));
		    	itemToState.put(menuItem, GuiStates.SMITHING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Fletching", new ImageIcon("resources/icons/fletching.png"));
		    	itemToState.put(menuItem, GuiStates.FLETCHING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Crafting", new ImageIcon("resources/icons/crafting.png"));
		    	itemToState.put(menuItem, GuiStates.CRAFTING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Herblore", new ImageIcon("resources/icons/herblore.png"));
		    	itemToState.put(menuItem, GuiStates.HERBLORE);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Firemaking", new ImageIcon("resources/icons/firemaking.png"));
		    	itemToState.put(menuItem, GuiStates.FIREMAKING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);	
		    menuItem = new JMenuItem("Runecrafting", new ImageIcon("resources/icons/runecrafting.png"));
		    	itemToState.put(menuItem, GuiStates.RUNFECRAFTING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Construction", new ImageIcon("resources/icons/construction.png"));
		    	itemToState.put(menuItem, GuiStates.CONSTRUCTION);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		target.add(menu);
		
		menu = new JMenu("Support");
		    menuItem = new JMenuItem("Agility", new ImageIcon("resources/icons/agility.png"));
		    	itemToState.put(menuItem, GuiStates.AGILITY);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Slayer", new ImageIcon("resources/icons/slayer.png"));
		    	itemToState.put(menuItem, GuiStates.SLAYER);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		    menuItem = new JMenuItem("Thieving", new ImageIcon("resources/icons/thieving.png"));
		    	itemToState.put(menuItem, GuiStates.THIEVING);
		    	menuItem.addActionListener(this);
		    	menu.add(menuItem);
		target.add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem currentItem = (JMenuItem) e.getSource();
		Settings.setCurrentGuiState(itemToState.get(currentItem));
	}
	
	//loads all of the initial gui types
	private void loadGuis() {
		botGui = new BotGui("bot");
		fishingGui = new FishingGui("fishing");
	}
	
	
}