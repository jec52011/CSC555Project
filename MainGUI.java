import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;


public class MainGUI extends JFrame {

	// Declare variables/necessary components for GUI window
	private JPanel panel;
	private JLabel welcome;
	private JButton createProfile, signIn;
	private final int W = 700;
	private final int H = 200;

	// MainGUI constructor, sets size of window, invokes buildPanel() method
	public MainGUI() {
		setTitle("CSC 555 Project");
		setSize(W, H);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buildPanel();
		add(panel);

		setVisible(true);
	}

	// buildPanel method which sets up labels/buttons and adds them to GUI
	private void buildPanel() {
		
		// Welcome label
		welcome = new JLabel("Welcome! Please sign in or create a profile.");
		welcome.setFont(new Font("Consolas", Font.BOLD, 25));

		// Create Profile and Sign In buttons
		createProfile = new JButton("Create Profile");
		createProfile.setFont(new Font("Consolas", Font.BOLD, 20));
		createProfile.addActionListener(new createListener());

		signIn = new JButton("Sign In");
		signIn.setFont(new Font("Consolas", Font.BOLD, 20));
		signIn.addActionListener(new signListener());

		// Panel setup
		panel = new JPanel();
		panel.add(welcome);
		panel.add(createProfile);
		panel.add(signIn);
	}
	
	
	// Action performed when user clicks Create Profile button. 
	private class createListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			new createGUI();
		}
	}
	
	// Action performed when user clicks Sign In button.
	private class signListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
	
	// Main which executes the whole program
	public static void main(String[] args)
	{
		new MainGUI();
	}

}
