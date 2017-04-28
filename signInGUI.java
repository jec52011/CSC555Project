import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// This class creates a GUI that allows the user to 'sign in' and authenticate
public class signInGUI extends JFrame implements KeyListener {
	// Declare variables/necessary components for GUI window
	private JPanel panel;
	private JLabel username, instructions;
	private JTextField get_username;
	private JTextArea staticText, get_staticText;
	private JButton signIn;
	private final int W = 700;
	private final int H = 500;

	private ArrayList<String> timeList = new ArrayList<String>();

	public signInGUI() {
		setTitle("Static Text Sign In");
		setSize(W, H);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		buildPanel();
		add(panel);

		setVisible(true);
	}

	// buildPanel method which sets up labels/buttons and adds them to GUI
	private void buildPanel() {

		// Username
		username = new JLabel("Please enter your username: ");
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

		// Authenticate button
		signIn = new JButton("Sign In");
		signIn.setFont(new Font("Consolas", Font.BOLD, 20));
		signIn.addActionListener(new verifyListener());

		// Panel setup
		panel = new JPanel();
		panel.add(username);
		panel.add(get_username);
		panel.add(instructions);
		panel.add(staticText);
		panel.add(get_staticText);
		panel.add(signIn);

	}

	// Uses R/A measure to verify a user signing in
	private class verifyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (validUsername(get_username.getText()) == true) {

				createTestFile();

				String[][] bioTemplate = fileToList(get_username.getText());
				Map<String, Integer> bioTempMap = digraphMap(bioTemplate);

				String[][] test = fileToList("testFile");
				Map<String, Integer> testMap = digraphMap(test);

				if (AMeasure(bioTempMap, testMap) == true) {
					infoBox("A Measure: PASS", "Blah blah");

				} else {
					infoBox("A Measure: FAIL", "Blah blah");
				}
			}

			else {
				infoBox("Error occurred. Invalid username.", "Warning!");
			}
		}
	}

	// Creates test biometric profile
	public void createTestFile() {

		try {
			PrintWriter outputFile = new PrintWriter("testFile.txt");
			pruneData();
			for (int i = 0; i < timeList.size(); i++) {
				outputFile.println(timeList.get(i));
			}

			outputFile.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	// Prints a 2D Array to console, NOT NECESSARY
	/*
	 * public static void print2DArray(String[][] ar) { for (int i = 0; i <
	 * ar.length; i++) { for (int j = 0; j < ar[0].length; j++) {
	 * System.out.print(ar[i][j] + " "); } System.out.println(); } }
	 */

	// Stores keystroke character/time data in arraylist
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

	// Calculates A Measure; NOTE: Order of map inputs matters!
	public boolean AMeasure(Map<String, Integer> bioTemplate, Map<String, Integer> test) {
		ArrayList<String> commonDigraphs = findCommonDigraphs(bioTemplate, test);
		float similarity = 0;

		for (int i = 0; i < commonDigraphs.size(); i++) {

			String key = commonDigraphs.get(i);

			float x = bioTemplate.get(key);
			float y = test.get(key);
			float ratio = Math.max(x, y) / Math.min(x, y);

			if (ratio < 1.25) {
				similarity++;
			}
		}

		float score = 1 - (similarity / commonDigraphs.size());
		System.out.println(score);

		if (score > .5) {
			return false;
		} else {
			return true;
		}

	}

	// Calculates R Measure;
	public boolean RMeasure(Map<String, Integer> bioTemplate, Map<String, Integer> test) {
		ArrayList<String> commonDigraphs = findCommonDigraphs(bioTemplate, test);
		digraph[] e1 = new digraph[commonDigraphs.size()];
		digraph[] e2 = new digraph[commonDigraphs.size()];

		// Add key-value pairs to above lists e1 and e2, based on common
		// digraphs
		// Figure out way to sort lists and find disorder
		for (int i = 0; i < commonDigraphs.size(); i++) {
			String key = commonDigraphs.get(i);

			digraph d1 = new digraph(key, bioTemplate.get(key));
			e1[i] = d1;

			digraph d2 = new digraph(key, test.get(key));
			e2[i] = d2;
		}

		bubbleSort(e1);
		bubbleSort(e2);

		int disorder = 0;
		int difference = 0;

		for (int i = 0; i < e1.length; i++) {
			for (int j = 0; j < e2.length; j++) {
				if (e1[i].key == e2[j].key) {
					difference = Math.abs(j - i);
				}
			}
			disorder = difference + disorder;
		}

		double maxDisorder;
		if (commonDigraphs.size() % 2 == 1) {
			maxDisorder = ((Math.pow(commonDigraphs.size(), 2)) - 1) / 2;
		} else {
			maxDisorder = Math.pow(commonDigraphs.size(), 2) / 2;

		}

		if (disorder / maxDisorder > .5) {
			return false;
		} else {
			return true;
		}

	}

	// Find common digraphs between 2 profile maps
	public ArrayList<String> findCommonDigraphs(Map<String, Integer> bioTemplate, Map<String, Integer> test) {
		ArrayList<String> cList = new ArrayList<String>();

		Set<String> testKeySet = test.keySet();
		String[] testKeys = testKeySet.toArray(new String[testKeySet.size()]);

		for (int i = 0; i < testKeys.length; i++) {
			if (bioTemplate.containsKey(testKeys[i])) {
				cList.add(testKeys[i]);
			}

		}

		return cList;
	}

	// Creates map of digraphs statistics (key = character pair, value = time)
	public Map<String, Integer> digraphMap(String[][] sList) {

		// Declare map, which will hold digraph stats
		Map<String, Integer> dList = new HashMap<String, Integer>();

		// For loop places key (char pair) and value (time difference) in map
		for (int i = 0; i < sList.length - 1; i++) {
			String key = sList[i][0] + sList[i + 1][0];
			int value = Integer.parseInt(sList[i + 1][1]) - Integer.parseInt(sList[i][1]);

			if (dList.containsKey(key) == false) {
				dList.put(key, value);
			} else {
				int average = (value + dList.get(key)) / 2;
				dList.put(key, average);
			}
		}
		return dList;
	}

	// Converts contents in file to 2D String array IS THIS CORRECT???
	public String[][] fileToList(String s) {
		String[][] numbers = new String[countLines(s)][];
		File file;
		Scanner inputFile = null;

		// Find the input file
		try {
			file = new File(s + ".txt");
			inputFile = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		// Place elements in 2D array using while loop
		try {
			int i = 0;

			while (inputFile.hasNext()) {
				String line = inputFile.nextLine();
				String[] keyPair = line.split(" ");
				numbers[i] = keyPair;
				i++;
			}

			inputFile.close();

		} catch (NullPointerException e) {
			System.out.println("Null pointer error");
		}
		return numbers;
	}

	// Count number of lines in a .txt file
	public int countLines(String s) {

		FileReader in = null;
		try {
			in = new FileReader(s + ".txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(in);
		int n = 0;

		try {
			while (br.readLine() != null) {
				n++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return n;
	}

	// Validate a username, make sure it is a username on file
	public boolean validUsername(String s) {

		// If s is null or empty, return false for invalid username.
		if (s == null || s.isEmpty()) {
			return false;
		}

		else {
			File file;
			Scanner inputFile = null;
			boolean currentUser = false;

			// If the try is successful, than we found a created file
			try {
				file = new File(s + ".txt");
				inputFile = new Scanner(file);
				currentUser = true;
			}

			// If catch is invoked, file was not found, we have invalid user.
			catch (FileNotFoundException e) {
			}

			return currentUser;
		}

	}

	// Bubble sort used for sorting digraphs from shortest to longest time
	public static void bubbleSort(digraph[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[j].value < array[i].value) {
					digraph temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	// Displays popup message
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

class digraph {
	String key;
	int value;

	public digraph(String key, int value) {
		this.key = key;
		this.value = value;
	}
}
