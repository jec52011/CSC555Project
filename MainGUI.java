import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class MainGUI extends JFrame {

	// Declare variables/necessary components for GUI window
	private JPanel panel;
	private JLabel welcome;
	private JButton createProfile1, signIn1, createProfile2, signIn2, createProfile3, signIn3;
	private final int W = 700;
	private final int H = 300;

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

		// Create Profile and Sign In buttons for STATIC SIGN IN
		createProfile1 = new JButton("Create STATIC TEXT Profile");
		createProfile1.setFont(new Font("Consolas", Font.BOLD, 20));
		createProfile1.addActionListener(new createListener1());

		signIn1 = new JButton("STATIC TEXT Sign In");
		signIn1.setFont(new Font("Consolas", Font.BOLD, 20));
		signIn1.addActionListener(new signListener1());

		// Create Profile and Sign In buttons for FREE TEXT SIGN IN
		createProfile2 = new JButton("Create FREE TEXT Profile");
		createProfile2.setFont(new Font("Consolas", Font.BOLD, 20));
		createProfile2.addActionListener(new createListener2());

		signIn2 = new JButton("FREE TEXT Sign In");
		signIn2.setFont(new Font("Consolas", Font.BOLD, 20));
		signIn2.addActionListener(new signListener2());

		// Create Profile and Sign In buttons for 10 PASSWORDS
		createProfile3 = new JButton("Create x10 PASSWORD Profile");
		createProfile3.setFont(new Font("Consolas", Font.BOLD, 20));
		createProfile3.addActionListener(new createListener3());

		signIn3 = new JButton("x10 PASSWORD Sign In");
		signIn3.setFont(new Font("Consolas", Font.BOLD, 20));
		signIn3.addActionListener(new signListener3());

		// Panel setup
		panel = new JPanel();
		panel.add(welcome);
		panel.add(createProfile1);
		panel.add(signIn1);
		panel.add(createProfile2);
		panel.add(signIn2);
		panel.add(createProfile3);
		panel.add(signIn3);
	}

	// Action performed when user clicks Create Profile button. STATIC
	private class createListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new createGUI();
		}
	}

	// Action performed when user clicks Sign In button. STATIC
	private class signListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new signInGUI();
		}
	}

	// Action performed when user clicks Create Profile button. FREE TEXT
	private class createListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	// Action performed when user clicks Sign In button. FREE TEXT
	private class signListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	// Action performed when user clicks Create Profile button. x10 PASSWORD
	private class createListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	// Action performed when user clicks Sign In button. x10 PASSWORD
	private class signListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	// Main which executes the whole program
	public static void main(String[] args) {
		new MainGUI();
	}

}
