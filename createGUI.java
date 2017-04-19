import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;



// This class creates a GUI for setting up a user profile.
public class createGUI extends JFrame implements KeyListener{
	// Declare variables/necessary components for GUI window
	private JPanel panel;
	private JLabel username, instructions;
	private JTextField get_username;
	private JTextArea staticText, get_staticText;
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
			
		}
	}


	// Figure out how to not record action keys.
	public void keyPressed(KeyEvent e) {
		
		if (48 <= e.getKeyCode() && e.getKeyCode() <= 90) { 
			System.out.println(e.getKeyCode() + " was pressed at time " 
					+ System.currentTimeMillis());
		}
		
		
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
