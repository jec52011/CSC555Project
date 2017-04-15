import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;


// This class creates a GUI for setting up a user profile.
public class createGUI extends JFrame {
	// Declare variables/necessary components for GUI window
	private JPanel panel;
	private JLabel username, staticText;
	private JTextField get_username;
	private JTextArea get_staticText;
	private JButton save;
	private final int W = 700;
	private final int H = 500;

	// createGUI constructor, sets size of window, invokes buildPanel() method
	public createGUI() {
		setTitle("Create Profile");
		setSize(W, H);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		buildPanel();
		add(panel);

		setVisible(true);
	}

	// buildPanel method which sets up labels/buttons and adds them to GUI
	private void buildPanel() {

		// Username
		username = new JLabel("Please enter a username: ");
		username.setFont(new Font("Consolas", Font.BOLD, 20));
		get_username = new JTextField(10);
		get_username.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		
		// Static text and accompanying text area
		String text = "All human beings are born free and\n equal in dignity and rights.";
		staticText = new JLabel("Enter the following text: " + text);
		staticText.setFont(new Font("Consolas", Font.PLAIN, 20));
		get_staticText = new JTextArea(2, 10);
		
		// Save button
		save = new JButton("Save");
		save.setFont(new Font("Consolas", Font.BOLD, 20));
		save.addActionListener(new saveDataListener());
		

		// Panel setup
		panel = new JPanel();
		panel.add(username);
		panel.add(get_username);
		panel.add(staticText);
		panel.add(get_staticText);
		panel.add(save);
	}

	// Action performed when user clicks Save button.
	private class saveDataListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}

}
