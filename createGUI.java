import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

// This class creates a GUI for setting up a user profile.
public class createGUI extends JFrame implements KeyListener {
	// Declare variables/necessary components for GUI window
	private JPanel panel;
	private JLabel username, instructions;
	private JTextField get_username;
	private JTextArea staticText, get_staticText;
	private JButton save;
	private final int W = 700;
	private final int H = 500;

	private ArrayList<String> timeList = new ArrayList<String>();

	// createGUI constructor, sets size of window, invokes buildPanel() method
	public createGUI() {
		setTitle("Create Static Text Profile: ");
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
		get_username = new JTextField(20);
		get_username.setFont(new Font("Consolas", Font.PLAIN, 18));

		// Typing instructions for user and accompanying static text to copy.
		instructions = new JLabel("Enter the following text: ");
		instructions.setFont(new Font("Consolas", Font.BOLD, 20));

		String text = "All human beings are born free and equal in dignity and rights.";
		staticText = new JTextArea(2, 60);
		staticText.setFont(new Font("Consolas", Font.ITALIC, 20));
		staticText.setWrapStyleWord(true);
		staticText.setLineWrap(true);
		staticText.setText(text);
		staticText.setEditable(false);

		// Area for user to type static text.
		get_staticText = new JTextArea(4, 30);
		get_staticText.setFont(new Font("Consolas", Font.PLAIN, 18));
		get_staticText.setWrapStyleWord(true);
		get_staticText.setLineWrap(true);
		get_staticText.addKeyListener(this);
		get_staticText.setFocusable(true);

		// Save button
		save = new JButton("Save");
		save.setFont(new Font("Consolas", Font.BOLD, 20));
		save.addActionListener(new saveDataListener());

		// Panel setup
		panel = new JPanel();
		panel.add(username);
		panel.add(get_username);
		panel.add(instructions);
		panel.add(staticText);
		panel.add(get_staticText);
		panel.add(save);
	}

	// Action performed when user clicks Save button.
	private class saveDataListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (validUsername(get_username.getText()) == true) {

				try {
					PrintWriter outputFile = new PrintWriter(get_username.getText() + ".txt");
					pruneData();
					for (int i = 0; i < timeList.size(); i++) {
						outputFile.println(timeList.get(i));
					}

					outputFile.close();

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				// Close window after save complete
				dispose();
			}

			else {
				infoBox("Error occurred. Invalid username.", "Warning!");
			}

		}
	}

	// Stores data in an array list
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() >= 48 && e.getKeyCode() <= 90 && e.getKeyCode() != 59) {
			timeList.add(e.getKeyCode() + " " + System.currentTimeMillis());
		}

	}

	// Convert the time to long, calculate time differences, prepare for R/A
	// measures
	public void pruneData() {

		String[] basePair = timeList.get(0).split(" ");
		long base = Long.parseLong(basePair[1]);

		for (int i = 0; i < timeList.size(); i++) {
			String[] keyPair = timeList.get(i).split(" ");
			timeList.set(i, keyPair[0] + " " + (Long.parseLong(keyPair[1]) - base));
		}

	}

	// Check that user entered a valid username.
	public boolean validUsername(String s) {

		// If s is null or empty, return false for invalid username.
		if (s == null || s.isEmpty()) {
			return false;
		}

		else {
			File file;
			Scanner inputFile = null;
			boolean newUser = true;

			// If the try is successful, than we found a duplicate file
			try {
				file = new File(s + ".txt");
				inputFile = new Scanner(file);
				newUser = false;
			}

			// If catch is invoked, file was not found and we have new username.
			catch (FileNotFoundException e) {

			}

			return newUser;
		}

	}

	public static void infoBox(String infoMessage, String titleBar) {

		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	private void displayInfo(KeyEvent e, String keyStatus) {

	}
}
