/**
 * ColourCreatorApp.java
 * The main application loop for the Colour Creator application.
 * 
 * @author RichardFlanagan(A00193644)
 */

package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



@SuppressWarnings("serial")	// Stops serial UID warning
public class ColourCreatorApp extends JFrame{
	
	private JButton colourInfo;
	private JButton colourDisplay;
	private ArrayList<JButton> redButtons = new ArrayList<JButton>();
	private ArrayList<JButton> greenButtons = new ArrayList<JButton>();
	private ArrayList<JButton> blueButtons = new ArrayList<JButton>();
	
	
	/**
	 * Constructor calls JFrame super(), sets vital JFrame attributes, and calls GUI creation function.
	 * @param title The title of the JFrame
	 */
	public ColourCreatorApp(String title){
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationByPlatform(true);
		setVisible(true);
		
		createComponents();
	}
	
	
	/**
	 * Creates the components and adds them into the JFrame.
	 */
	private void createComponents(){
		JPanel colourPanel = new JPanel();
		colourPanel.add(createButtonPanel("red"));
		colourPanel.add(createButtonPanel("green"));
		colourPanel.add(createButtonPanel("blue"));
		
		
		JPanel infoPanel = new JPanel(new GridLayout());
		
		colourInfo = new JButton("Colour Selected was R:0, G:0, B:0");
		colourInfo.setBackground(Color.WHITE);
		colourInfo.addActionListener(new ColourInfoButtonActionListener());
		infoPanel.add(colourInfo);
		
		colourDisplay = new JButton();
		colourDisplay.setEnabled(false);
		colourDisplay.setBackground(Color.BLACK);
		infoPanel.add(colourDisplay);
		
	
		add(colourPanel, BorderLayout.NORTH);
		add(infoPanel, BorderLayout.SOUTH);
		pack();
	}

	
	/**
	 * Creates a panel of colored buttons. The colors increase by the supplied parameters. Gradient from black to Color(r*n, g*n, b*n).
	 * 
	 * @param rgbColour The string name of the desired RGB base colour (red, green or blue).
	 * @return The JPanel of colored JButtons.
	 */
	private JPanel createButtonPanel(String rgbColour){
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(20, 13));
		
		JButton button;
		Color colour;
		Dimension buttonSize = new Dimension(15, 30);
		ColourButtonActionListener actionListener = new ColourButtonActionListener();
		
		for(int i=0; i<256; i++){
			button = new JButton();
			colour = Color.WHITE;

			if (rgbColour.equals("red")){
				colour = new Color(i, 0, 0);
				redButtons.add(button);
			}
			else if (rgbColour.equals("green")){
				colour = new Color(0, i, 0);	
				greenButtons.add(button);
			}
			else if (rgbColour.equals("blue")){
				colour = new Color(0, 0, i);
				blueButtons.add(button);
			}
			
			button.setPreferredSize(buttonSize);
			button.setBorder(BorderFactory.createLineBorder(colour));
			button.setBackground(colour);
			button.addActionListener(actionListener);
			panel.add(button);
		}
		return panel;
	}
	
	
	/**
	 * ColourButtonActionListener.java
	 * Handles action for the coloured buttons in the ColorCreatorApp.
	 * 
	 * @author RichardFlanagan(A00193644)
	 */
	public class ColourButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) (e.getSource());
			Color c = b.getBackground();
			int red = 0;
			int green = 0;
			int blue = 0;
			
			if (redButtons.contains(b)){
				red = c.getRed();
				green = colourDisplay.getBackground().getGreen();
				blue = colourDisplay.getBackground().getBlue();
			}
			else if (greenButtons.contains(b)){
				red = colourDisplay.getBackground().getRed();
				green = c.getGreen();
				blue = colourDisplay.getBackground().getBlue();
			}
			else if (blueButtons.contains(b)){
				red = colourDisplay.getBackground().getRed();
				green = colourDisplay.getBackground().getGreen();
				blue = c.getBlue();
			}
			
			colourInfo.setText("Colour Selected was R:"+red+", G:"+green+", B:"+blue);
			colourDisplay.setBackground(new Color(red, green, blue));
		}
	}
	
	
	/**
	 * ColourInfoButtonActionListener.java
	 * Handles action for the ColourInfo button in the ColorCreatorApp. Used to reset it to default on click.
	 * 
	 * @author RichardFlanagan(A00193644)
	 */
	public class ColourInfoButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			colourInfo.setText("Colour Selected was R:0, G:0, B:0");
			colourDisplay.setBackground(Color.BLACK);
		}
	}
}
