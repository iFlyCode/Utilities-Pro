package ifly6.utilitiesPro4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ifly6.UtilitiesPro3.UtilitiesPro;
import ifly6.utilitiesPro4.config.UPVersion;

public class UtilitiesPro4 implements UPOutput {
	
	// TODO Make it call the command parser, now utilise output as UPOutput interface
	// TODO Make all the command bars and what not

	public static final UPVersion	version	= new UPVersion(4, 0, 0, true);
	private static final Logger		log		= Logger.getLogger(UtilitiesPro4.class.getName());
	
	private static String computerName;
	{
		if (computerName == null) {
			try {
				computerName = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				try {
					computerName = InetAddress.getLocalHost().getHostName();
				} catch (UnknownHostException e1) {
					System.out.println("Localhost Name-Get failed.");
				}
			}
		}
	}
	
	private File currentDir = new File(System.getProperty("user.dir"));
	
	private JFrame		frame;
	private JTextArea	textArea;
	private JTextField	inputField;
	
	public UtilitiesPro4() {
		initialise();
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override public void run() {
				try {
					UtilitiesPro4 window = new UtilitiesPro4();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void initialise() {

		frame = new JFrame("Utilities Pro " + version.toString());
		frame.setSize(500, 600);
		frame.setLocation(100, 100);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout(5, 5));
		frame.setContentPane(panel);
		
		JPanel txtPanel = new JPanel();
		txtPanel.setBorder(new EmptyBorder(3, 3, 3, 3));
		txtPanel.setLayout(new BorderLayout());
		
		textArea = new JTextArea("");
		textArea.setBorder(new EmptyBorder(5, 5, 5, 5));
		txtPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		panel.add(txtPanel, BorderLayout.CENTER);

		inputField = new JTextField();
		inputField.addKeyListener(new KeyListener() {

			@Override public void keyTyped(KeyEvent e) {
			}
			
			@Override public void keyReleased(KeyEvent e) {
			}

			@Override public void keyPressed(KeyEvent e) {
				
				if (e.equals(KeyEvent.VK_ENTER)) {
					
					outAsTerminal("");
					parse(inputField.getText());
					
				} else if (e.equals(KeyEvent.VK_TAB)) {
					inputField.setText(tabComplete(inputField.getText()));
				}
				
			}
		});
		panel.add(inputField, BorderLayout.SOUTH);
		
		frame.setVisible(true);

	}

	/**
	 * @param string
	 */
	protected void outAsTerminal(String input) {
		out(computerName + ": " + System.getProperty("user.name") + "$" + input);
	}

	protected void parse(String text) {

		if (text.startsWith("cd") && UPUtilities.isSplittable(text, " ")) {
			cd(text);
		}
		
		// Next, call the execution engine, provide it this window for output.

	}

	@Override public void append(String input) {
		textArea.append(" " + input + "\n");
	}

	/**
	 * Uses advanced recognition technology to auto-complete what is being looked for. Since 3.3_dev01, it also includes
	 * directory confirmation, and support for space-escape auto-formatting. Since 3.3_dev02, it also includes infinite
	 * file list comparation.
	 *
	 * @since 3.3
	 */
	private String tabComplete(String input) {

		String[] operand = input.split("(?<!\\\\)\\s+");
		String[] dirLayer = operand[operand.length - 1].split("/");
		
		boolean absPath = operand[operand.length - 1].startsWith("/");
		boolean usrPath = operand[operand.length - 1].startsWith("~");
		
		String lookDir = this.currentDir.getAbsolutePath();
		try {
			lookDir = this.currentDir.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("Raw: " + input);
		log.info("Search Term: " + dirLayer[dirLayer.length - 1]);

		if ((dirLayer.length - 1) >= 1) {
			StringBuilder builder = new StringBuilder();

			if (absPath) { // Is it an absolute Path?
				for (int x = 0; x < (dirLayer.length - 1); x++) {
					builder.append("/" + dirLayer[x]);
				}
			} else if (usrPath) { // Is it a ~ Path?
				builder.append(System.getProperty("user.home"));
				for (int x = 1; x < (dirLayer.length - 1); x++) {
					builder.append("/" + dirLayer[x]);
				}
			} else {
				builder.append(this.currentDir);
				for (int x = 0; x < (dirLayer.length - 1); x++) {
					builder.append("/" + dirLayer[x]);
				}
			}
			lookDir = builder.toString();
			log.info("LOG: Looking At " + lookDir);
		}

		String[] fileList = new File(lookDir).list();
		ArrayList<String> narrowList = new ArrayList<String>();

		for (String element : fileList) {
			if (operand[0].equals("cd")) { // With Directory Confirmation!
				if (element.startsWith(dirLayer[dirLayer.length - 1]) && new File(lookDir + "/" + element).isDirectory()) {
					narrowList.add(element);
				}
			} else { // To get completed file names!
				if (element.startsWith(dirLayer[dirLayer.length - 1])) {
					narrowList.add(element);
				}
			}
		}

		if (narrowList.isEmpty()) { // No Matches
			out("No Directory Match for keyword: " + dirLayer[dirLayer.length - 1]);
			
		} else if (narrowList.size() == 1) { // One Match

			StringBuilder builder = new StringBuilder();
			String selected = narrowList.get(0);
			String reinput = narrowList.get(0);

			// Deal with possible spaces.
			if (selected.contains(" ")) {
				selected = selected.replace(" ", "\\ ");
			}

			// Deal with possible Path Issues
			if (absPath || usrPath) {
				selected = lookDir + "/" + selected;
			}

			operand[operand.length - 1] = selected; // Load

			// Recombine the String
			for (String element : operand) {
				builder.append(element + " ");
			}

			reinput = builder.toString().trim();
			return reinput;

		} else if (narrowList.size() > 1) { // More than 1 Match
			out("There is more than one option available. Please continue typing.");
			for (String element : narrowList) {
				out(" * " + element);
			}

			return inputField.getText();
		}

		// If none of these Returns are triggered, return what was already typed in.
		return inputField.getText();
	}

	/**
	 * The CD entire Subsystem. Much waiting was done for this. One epiphany later, it was solved. Updated in 3.1 to
	 * include way of dealing with spaces in filenames. However, when the entire space system was overhauled in 3.2, it
	 * became unnecessary due to the escape char for space ('\ '). Since 3.1_03, it also checks whether the DIR you are
	 * trying to go to actually exists.
	 *
	 * @since 3.0_dev09.03
	 * @param operand - The command which was put in. This command can begin with anything, but when called, should only
	 *            being with 'cd'.
	 */
	public void cd(String input) {

		String[] operand = input.split("(?<!\\\\)\\s+");
		
		String dirExists = "The directory you are looking for does not exist, is not a directory, or there has been an error.";
		String dirPerms = "The directory have selected is restricted";

		try {
			
			// Deal with Files that start with '/'
			if (operand[1].startsWith("/")) {

				File newDir = new File(operand[1]);
				if (newDir.isDirectory() && newDir.canRead()) {
					
					this.currentDir = new File(operand[1]);
					
				} else if (!(new File(operand[1]).canRead())) {
					out(dirPerms);
				} else {
					out(dirExists);
				}
			}

			// Deal with things that start with '~'
			else if (operand[1].startsWith("~")) {

				File newDir = new File(operand[1].replaceAll("~", System.getProperty("user.home")));
				if (newDir.isDirectory() && newDir.canRead()) {
					
					this.currentDir = newDir;
					
				} else if (!newDir.canRead()) {
					out(dirPerms);
				} else {
					out(dirExists);
				}
				
			}

			// Deal with Everything Else
			else {
				
				File newDir = new File(UtilitiesPro.currentDir + "/" + operand[1]);
				if (newDir.isDirectory() && newDir.canRead()) {
					
					currentDir = new File(UtilitiesPro.currentDir + "/" + operand[1]);
					
				} else if (!newDir.canRead()) {
					out(dirPerms);
				} else {
					out(dirExists);
				}
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			out("Add argument to change directory command.");
		}
	}

	private void out(String input) {
		textArea.append(input + "\n");
	}
	
}
