package gui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import file.FileUtilities;
import main.Settings;

public class ImageProcessingGui {
	//these control the sliders - they are the values inside sliderToValue hashmap, while sliders are keys
	private static int rMinV;
	private static int rMaxV;
	private static int gMinV;
	private static int gMaxV;
	private static int bMinV;
	private static int bMaxV;
	
	static JPanel imgPanel;
	private static JPanel mainPanel;
	
	//each slider is connected with corresponding text field vice versa
	private static Map<JSlider, JFormattedTextField> sliderToField = new HashMap<>();
	private static Map<JFormattedTextField, JSlider> fieldToSlider = new HashMap<>();
	
	//connection between sliders and instance fields rMinV, etc
	private static Map<JSlider, Integer> sliderToValue = new HashMap<>();
	
	//holds all of the sliders
	private static List<JSlider> sliders = new ArrayList<>();
	
	private static int[] pixelRange = new int[6];
	
	private static GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * Creates the gui which enables you to process images (set rgb ranges, etc)
	 * @return
	 */
	public static JPanel createImageProcessingGui() {
		System.out.println("[ImageProcessingGui.createImageProcessingGui]: Creating image processing gui");
		
		mainPanel = new JPanel(new GridBagLayout());
		    setupMainPanel(mainPanel);
		
		JPanel toolBar = createToolbar(mainPanel);
		    JPanel other = createOtherPanel(toolBar);
		        setupOtherPanel(other);
		    JPanel rgbControl = createRGBControl(toolBar);
		        setupRGBControl(rgbControl);
		
		imgPanel = createImgPanel(mainPanel);
		
		System.out.println("[ImageProcessingGui.createImageProcessingGui]: Amount of components in main panel: " + mainPanel.getComponentCount());
		return mainPanel;
	}
	
	private static void setupMainPanel(JPanel target) {
		target.setBackground(new Color(24, 52, 64));
	}
	
	/**
	 * Creates the toolbar panel and adds it to the target panel and returns the toolbar panel
	 * @param target
	 */
	private static JPanel createToolbar(JPanel target) {
		JPanel toolBar = new JPanel(new GridLayout(2, 1, 5, 5));
		//toolBar.setBorder(BorderFactory.createLineBorder(Color.red));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 250;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		target.add(toolBar, gbc);
		return toolBar;
	}
	
	/**
	 * Creates the image panel, adds it to the target panel and returns a reference to it
	 * @param target
	 * @return
	 */
	private static JPanel createImgPanel(JPanel target) {
		JPanel imgPanel = new JPanel();
		/*
		JLabel info = new JLabel("image panel");
		imgPanel.add(info);
		imgPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		*/
		GridLayout gl = new GridLayout(2, 1);
		gl.setVgap(5);
		imgPanel.setLayout(gl);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.ipadx = 790;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		imgPanel.setBackground(new Color(24, 52, 64));
		target.setBackground(new Color(24, 52, 64));
		target.add(imgPanel, gbc);
		return imgPanel;
	}
	
	/**
	 * Create the "other" panel, which contains saving buttons and rgb saver
	 * @param target
	 * @return
	 */
	private static JPanel createOtherPanel(JPanel target) {
		JPanel other = new JPanel();
		other.setLayout(new BoxLayout(other, BoxLayout.PAGE_AXIS));
		//other.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		target.setBackground(new Color(24, 52, 64));
		target.add(other);
		return other;
	}
	
	/**
	 * Sets up the "other" panel, which contains the saving button and the rgb range saver
	 * @param target
	 */
	private static void setupOtherPanel(JPanel target) {	
		JPanel savingPanel = new JPanel();
		
		JButton savingButton = new JButton("Enable saving");
		savingButton.setBackground(new Color(96, 96, 105));
		savingButton.setForeground(Color.WHITE);
		savingButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel savingLabel = new JLabel("<html>Saving is now <font color='red'>disabled</font></html>");
		savingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//set up listener for savingButton
		savingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Settings.savingEnabled == false) {
					Settings.savingEnabled = true;
				} else {
					Settings.savingEnabled = false;
				}
				
				if (Settings.savingEnabled == false) {
					//set saving button to red
					savingButton.setText("Enable saving");
					savingButton.setBackground(new Color(181, 43, 31));
					
					savingLabel.setText("<html>Saving is now <font color='red'>disabled</font></html>");
				} else {
					//set saving button to red
					savingButton.setText("Disable saving");
					savingButton.setBackground(new Color(60, 200, 50));
					
					savingLabel.setText("<html>Saving is now <font color='green'>enabled</font></html>");
				}
			}
		});
		
		savingPanel.add(savingButton);
		savingPanel.add(savingLabel);
		savingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		savingPanel.setBackground(new Color(252, 255, 249));
		
		target.add(savingPanel);
		
		setupRGBSaver(target);
	}
	
	private static void setupRGBSaver(JPanel target) {
		JPanel rgbRangeSaver = new JPanel(new GridBagLayout());
		//rgbRangeSaver.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		rgbRangeSaver.setAlignmentX(Component.LEFT_ALIGNMENT);
		rgbRangeSaver.setBackground(new Color(252, 255, 246));
		
		//used to specify info about rgb saver
		JLabel rgbRangeSaverInfo = new JLabel("Save the RGB range set to file");
		rgbRangeSaverInfo.setPreferredSize(new Dimension(200, 40));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.00;
		gbc.weighty = 0.00;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		rgbRangeSaver.add(rgbRangeSaverInfo, gbc);
		
		JLabel rgbRangeNameL = new JLabel("Range set name:");
		rgbRangeNameL.setPreferredSize(new Dimension(80, 20));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.00;
		gbc.weighty = 0.00;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		rgbRangeSaver.add(rgbRangeNameL, gbc);
		
		//used to create the name under whcih to save this set to file
		JFormattedTextField rgbRangeName = new JFormattedTextField();
		rgbRangeName.setPreferredSize(new Dimension(120, 20));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.00;
		gbc.weighty = 0.00;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		rgbRangeSaver.add(rgbRangeName, gbc);
		
		//used to save 
		JButton saveRGBRange = new JButton("Save RGB range to disk.");
		saveRGBRange.setPreferredSize(new Dimension(80, 20));
		saveRGBRange.setBackground(new Color(24, 52, 64));
		saveRGBRange.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.00;
		gbc.weighty = 0.00;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		
		saveRGBRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != rgbRangeNameL.getText()) {
					saveRGBRange(rgbRangeName.getText());
				}				
			}
			
		});
		rgbRangeSaver.add(saveRGBRange, gbc);
		
		//displays nothing, used to prevent components from getting clumped in the middle
		JLabel controlLabel = new JLabel();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		rgbRangeSaver.add(controlLabel, gbc);
		
		target.add(rgbRangeSaver);
	}
	
	/**
	 * Creates the panel which is to contain all of the rgb range sliders
	 * @param target
	 * @return
	 */
	private static JPanel createRGBControl(JPanel target) {
		JPanel rgbControl = new JPanel(new GridLayout(6, 3));
		//rgbControl.setBorder(BorderFactory.createLineBorder(Color.pink));
		rgbControl.setBackground(new Color(252, 255, 249));
		target.add(rgbControl, gbc);
		return rgbControl;
	}
	
	/**
	 * Adds the sliders to the rgbcontrol panel
	 * @param target
	 */
	private static void setupRGBControl(JPanel target) {
		int min = 0;
		int max = 255;
		
		JLabel rMinL = new JLabel("Red minimum:");
		rMinL.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		JSlider rMin = new JSlider(JSlider.HORIZONTAL, min, max, 0);
		sliders.add(rMin);
		JFormattedTextField rMinTF = new JFormattedTextField();
		sliderToField.put(rMin, rMinTF);
		sliderToValue.put(rMin, rMinV);
		fieldToSlider.put(rMinTF, rMin);
		target.add(rMinL);
		target.add(rMin);
		target.add(rMinTF);
		
		JLabel rMaxL = new JLabel("Red maximum:");
		rMaxL.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		JSlider rMax = new JSlider(JSlider.HORIZONTAL, min, max, 0);
		sliders.add(rMax);
		JFormattedTextField rMaxTF = new JFormattedTextField();
		sliderToField.put(rMax, rMaxTF);
		sliderToValue.put(rMax, rMaxV);
		fieldToSlider.put(rMaxTF, rMax);
		target.add(rMaxL);
		target.add(rMax);
		target.add(rMaxTF);
		
		JLabel gMinL = new JLabel("Green minimum:");
		gMinL.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		JSlider greenMin = new JSlider(JSlider.HORIZONTAL, min, max, 0);
		sliders.add(greenMin);
		JFormattedTextField gMinTF = new JFormattedTextField();
		sliderToField.put(greenMin, gMinTF);
		sliderToValue.put(greenMin, gMinV);
		fieldToSlider.put(gMinTF, greenMin);
		target.add(gMinL);
		target.add(greenMin);
		target.add(gMinTF);
		
		JLabel gMaxL = new JLabel("Green maximum:");
		gMaxL.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		JSlider greenMax = new JSlider(JSlider.HORIZONTAL, min, max, 0);
		sliders.add(greenMax);
		JFormattedTextField gMaxTF = new JFormattedTextField();
		sliderToField.put(greenMax, gMaxTF);
		sliderToValue.put(greenMax, gMaxV);
		fieldToSlider.put(gMaxTF, greenMax);
		target.add(gMaxL);
		target.add(greenMax);
		target.add(gMaxTF);
		
		JLabel bMinL = new JLabel("Blue minimum:");
		bMinL.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		JSlider blueMin = new JSlider(JSlider.HORIZONTAL, min, max, 0);
		sliders.add(blueMin);
		JFormattedTextField bMinTF = new JFormattedTextField();
		sliderToField.put(blueMin, bMinTF);
		sliderToValue.put(blueMin, bMinV);
		fieldToSlider.put(bMinTF, blueMin);
		target.add(bMinL);
		target.add(blueMin);
		target.add(bMinTF);
		
		JLabel bMaxL = new JLabel("Blue maximum:");
		bMaxL.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);
		JSlider blueMax = new JSlider(JSlider.HORIZONTAL, min, max, 0);
		sliders.add(blueMax);
		JFormattedTextField bMaxTF = new JFormattedTextField();
		sliderToField.put(blueMax, bMaxTF);
		sliderToValue.put(blueMax, bMaxV);
		fieldToSlider.put(bMaxTF, blueMax);
		target.add(bMaxL);
		target.add(blueMax);
		target.add(bMaxTF);
		
		setSliderProperties(rMin, rMax, blueMin, blueMax, greenMin, greenMax);
		
		//sets the properties of formatted text fields
		setTFProperties(rMinTF, rMaxTF, bMinTF, bMaxTF, gMinTF, gMaxTF);
		
	}
	
	/**
	 * This method adds change listeners for the specified sliders
	 * @param sliders
	 */
	private static void setSliderProperties(JSlider...slrs) {
		for (JSlider s : slrs) {
			s.setBackground(new Color(252, 255, 249));
			s.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					//System.out.println("Slider value: " + s.getValue());
					JFormattedTextField correspondingTF = sliderToField.get(s);
					correspondingTF.setValue(s.getValue());
					
					if (s.getValue() < 0) {
						s.setValue(0);
					} else if (s.getValue() > 255) {
						s.setValue(255);
					}
					
					//updates the value of the slider (as key) in slideToValue hashmap
					sliderToValue.put(s, s.getValue());
					
					//update pixelRange
					int i = 0;
					for (JSlider sl : sliders) {
						pixelRange[i] = sliderToValue.get(sl);
						i++;
					}
				}
				
			});
		}
	}
	
	/**
	 * This method sets the properties of formatted text field along with their change listeners
	 * @param fields
	 */
	private static void setTFProperties(JFormattedTextField...fields) {
		for (JFormattedTextField f : fields) {
			f.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
			NumberFormat nf = NumberFormat.getIntegerInstance();
			NumberFormatter nfr = new NumberFormatter(nf);
			DefaultFormatterFactory dff = new DefaultFormatterFactory(nfr);
			f.setFormatterFactory(dff);
			
			f.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent e) {
					int min = 0;
					int max = 255;
					int value;
					
					try {
						value = Integer.parseInt(f.getText());
						
						//if value in range get corresponding slider and set it to value set by the text field
						if (value > min && value < max) {
							JSlider correspondingSlider = fieldToSlider.get(f);
							if (null != f.getValue()) {
								correspondingSlider.setValue(Integer.parseInt(f.getText()));
							}
							 if (value < min) {
								 correspondingSlider.setValue(0);
								 f.setText("0");
							 }
							 if(value > max) {
								 correspondingSlider.setValue(255);
								 f.setText("255");
							 }
						}
					} catch (NumberFormatException p) {
						//p.printStackTrace(); don+t print this out
					}
					
					JSlider correspondingSlider = fieldToSlider.get(f);
					if (null != f.getValue()) {
						correspondingSlider.setValue(Integer.parseInt(f.getText()));
					}
				}
				
			});
		}
	}
	
	/**
	 * This method saves the rgb set with the specified name to disk
	 * @param rangeName
	 */
	private static void saveRGBRange(String rangeName) {
		String filePath = "resources/rgbRangeSets.txt";
		
		if (sliders.size() == 6) {
			rMinV = sliders.get(0).getValue();
			rMaxV = sliders.get(1).getValue();
			gMinV = sliders.get(2).getValue();
			gMaxV = sliders.get(3).getValue();
			bMinV = sliders.get(4).getValue();
			bMaxV = sliders.get(5).getValue();
		} else {
			System.err.println("[GUIManager.saveRGBRange]: Improper size of sliders! Supporting only rgb range of size 6.");
		}
		
		int[] sliderValues = new int[] {rMinV, rMaxV, gMinV, gMaxV, bMinV, bMaxV};
		FileUtilities.writeToFile(filePath, rangeName, sliderValues);
	}
	
	/**
	 * This method draws the specified image inside the specified borders (of the specified frame) and scales it on the fly to fit
	 * inside these borders
	 * @param frame The JFrame instance to which to draw on
	 * @param img The image to be drawn, specified as BufferedImage
	 * @param dstx1 The x coordinate of the upper-left corner of the destination rectangle
	 * @param dsty1 The y coordinate of the upper-left corner of the destination rectangle
	 * @param dstx2 The x coordinate of the bottom-right corner of the destination rectangle
	 * @param dsty2 The y coordinate of the bottom.right corner of the destination rectangle
	 * @param srcx1 The x coordinate of the upper-left corner of the source image
	 * @param srcy1 The y coordinate of the upper-left corner of the source image
	 * @param srcx2 The x coordinate of the bottom.right corner of the source image
	 * @param srcy2 The y coordinate of the bottom-right corner of the source image
	 */
	public static void paint(JFrame frame, BufferedImage img, int dstx1, int dsty1, int dstx2, int dsty2,
		       int srcx1, int srcy1, int srcx2, int srcy2) {
		Graphics g = frame.getGraphics();
		g.drawImage(img, dstx1, dsty1, dstx2, dsty2, srcx1, srcy1, srcx2, srcy2, null);
	}
	
	public static List<JSlider> getSliders() {
		return sliders;
	}
	
	public static int[] getPixelRange() {
		return pixelRange;
	}
	
	public static JPanel getImgPanel() {
		return imgPanel;
	}
	
	public static JPanel getMainPanel() {
		return mainPanel;
	}
}
