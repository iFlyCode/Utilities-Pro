package com.me.ifly6;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

import com.me.ifly6.Commands.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Console extends JFrame implements KeyListener, ActionListener{
	// Name: Console (Also the Main Class)

	/*
	 * THINGS TO DO:
	 * IMPLEMENT A CHANGE DIRECTORY SYSTEM.
	 */

	private static final long serialVersionUID = 1L;

	// EXTERNAL DATA
	protected static String computername = "Unknown";
	public static int numArray = 20;

	// SWING DATA
	static JFrame frame = new JFrame("iUtilities " + Info.version);
	JPanel pane = new JPanel();
	public static JTextArea display = new JTextArea();
	public static JTextArea output = new JTextArea();
	public static JTextArea log = new JTextArea();
	public static JTextField input = new JTextField();
	JScrollPane scp = new JScrollPane(display);

	// INTERNAL DATA
	public static String preoperand;
	public static String[] operand;
	static String[] mem = new String[10];
	static final String starter = "== iUtilities Console " + Info.version + " == " + 
			"\nHello " + System.getProperty("user.name") + "!" + 
			"\nType 'help' for help.";
	public static int screen_state = 0;
	public static String screen_stored = starter;

	JMenuBar menubar = new JMenuBar();
	JMenu menufile = new JMenu("File");
	JMenu menucomm = new JMenu("Commands");
	JMenu menuview = new JMenu("View");
	JMenu menuhelp = new JMenu("Help");
	JMenuItem export = new JMenuItem("Exportation");
	JMenuItem script = new JMenuItem("Script Input");
	JMenuItem mindterm = new JMenuItem("Mindterm");
	JMenuItem purge = new JMenuItem("Inactive Memory Purge");
	JMenuItem debug = new JMenuItem("Log Console");
	JMenuItem info = new JMenuItem("System Readout");
	JMenuItem clear = new JMenuItem("Clear Screen");
	JMenuItem defaultCarat = new JMenuItem("Snap to Bottom");
	JMenuItem viewswitch = new JMenuItem("Switch View");
	JMenuItem del = new JMenuItem("Delete iUtilities Files");
	JMenuItem term = new JMenuItem("Terminate Process");
	JMenuItem about = new JMenuItem("About");
	JMenuItem help = new JMenuItem("Help");
	JMenuItem changelog = new JMenuItem("Changelog");
	JMenuItem updates = new JMenuItem("Updates");

	Console()
	{
		// Base GUI, in Swing.
		frame.setBounds(50, 50, 670, 735);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pane.setLayout(new BorderLayout());
		frame.getContentPane().add(pane);
		scp.setViewportBorder(new LineBorder(new Color(0, 0, 0), 6));

		pane.add(scp, BorderLayout.CENTER);
		input.setToolTipText("Type Commands Here");
		pane.add(input, BorderLayout.SOUTH);
		input.addKeyListener(this);

		Font font = new Font("Monaco", 0, 11);
		display.setEditable(false);
		display.setFont(new Font("Monaco", Font.PLAIN, 11));
		display.setBackground(new Color(0, 0, 0));
		display.setForeground(Color.green);

		input.setFont(font);
		input.setBackground(Color.black);
		input.setForeground(Color.green);
		input.setCaretColor(Color.green);
		pane.setBackground(Color.DARK_GRAY);
		DefaultCaret caret = (DefaultCaret)display.getCaret();
		caret.setUpdatePolicy(2);
		menubar.setMargin(new Insets(0, 2, 0, 0));
		menubar.setBackground(Color.LIGHT_GRAY);
		menubar.setForeground(Color.BLACK);
		menufile.setHorizontalAlignment(SwingConstants.LEFT);
		menufile.setBackground(Color.LIGHT_GRAY);
		menufile.setForeground(Color.BLACK);

		// MENUBAR CREATION
		menubar.add(menufile);
		menucomm.setHorizontalAlignment(SwingConstants.LEFT);
		menucomm.setBackground(Color.LIGHT_GRAY);
		menucomm.setForeground(Color.BLACK);
		menubar.add(menucomm);
		menuview.setHorizontalAlignment(SwingConstants.LEFT);
		menuview.setBackground(Color.LIGHT_GRAY);
		menuview.setForeground(Color.BLACK);
		menubar.add(menuview);
		menuhelp.setHorizontalAlignment(SwingConstants.LEFT);
		menuhelp.setBackground(Color.LIGHT_GRAY);
		menuhelp.setForeground(Color.BLACK);
		menubar.add(menuhelp);
		export.setBackground(Color.WHITE);
		export.setForeground(Color.BLACK);

		// File
		menufile.add(export);
		script.setBackground(Color.WHITE);
		script.setForeground(Color.BLACK);
		menufile.add(script);
		mindterm.setBackground(Color.WHITE);
		mindterm.setForeground(Color.BLACK);
		menufile.add(mindterm);
		export.addActionListener(this);
		script.addActionListener(this);
		mindterm.addActionListener(this);
		// Commands
		menucomm.add(purge);
		menucomm.add(debug);
		menucomm.add(info);
		menucomm.add(term);
		purge.addActionListener(this);
		debug.addActionListener(this);
		info.addActionListener(this);
		term.addActionListener(this);
		// View
		menuview.add(clear);
		menuview.add(defaultCarat);
		menuview.add(viewswitch);
		menuview.add(del);
		clear.addActionListener(this);
		defaultCarat.addActionListener(this);
		viewswitch.addActionListener(this);
		del.addActionListener(this);
		// Help
		menuhelp.add(about);
		menuhelp.add(help);
		menuhelp.add(changelog);
		menuhelp.add(updates);
		about.addActionListener(this);
		help.addActionListener(this);
		changelog.addActionListener(this);
		updates.addActionListener(this);

		pane.add(menubar, BorderLayout.NORTH);
		frame.setVisible(true);
		log.append("\nJava Swing GUI Initialised and Rendered");
	}

	// MAIN THREAD.
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		new Console();

		// OS Restriction
		if (isWindows()){
			frame.setVisible(false);
			log.append("\nWindows Detected. Disengaging.");
			String temp = "Windows Detected. Disengaging to prevent havoc, as this is requires UNIX Commands.";
			JOptionPane.showMessageDialog(null, temp, "OS Validation", -1);
			Thread.sleep(10000);
			System.exit(0);
		}

		// Visible Housekeeping
		display.append(starter);
		computername = InetAddress.getLocalHost().getHostName();
		Date date = new Date();
		log.append("\niUtilities " + Info.version + " Initialised. Date: " + date);

		// Invisible Housekeeping
		Addons.array_fill();
	}

	// EVENT HANDLER
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 10) {
			try {
				TextProc.proc();
			} catch (IOException e1) { log.append("\nkeyPressed Error, IO Exception"); 
			} catch (InterruptedException e1) { log.append("\nkeyPressed Error, InterruptedException"); }
		}
		if (keyCode == 38){ input.setText(preoperand); }
	}
	public void keyReleased(KeyEvent arg0) { }
	public void keyTyped(KeyEvent arg0) { }

	// ACTIONPREFORMED LISTENER FOR ALL THE MENU BUTTONS
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == export) {
			display.append("\n" + computername + "~ $ File>Export ");
			try {
				AssortedMethods.save();
			} catch (IOException e1) { log.append("\nExport Failed, IOException"); }
		}
		if (eventSource == script) {
			display.append("\n" + computername + "~ $ File>Script");
			AssortedMethods.script();
		}
		if (eventSource == mindterm) {
			display.append("\n" + computername + "~ $ File>Mindterm");
			try {
				AssortedMethods.mindterm();
			} catch (IOException e1) { log.append("\nMindterm Download Failed: IOException"); }
		}
		if (eventSource == purge) {
			display.append("\n" + computername + "~ $ Command>Purge");
			try {
				AssortedMethods.purge();
			} catch (IOException e1) { log.append("\nPurge Failed: IOException");}
		}
		if (eventSource == debug) {
			ConsoleIf.append("\n" + computername + "~ $ Command>Debug");
			try {
				AssortedMethods.saveLog();
			} catch (IOException e1) { log.append("\nBug JTextArea Export Failed: IOException"); }
		}
		if (eventSource == info){
			ConsoleIf.append(computername + "~ $ Command>System Readout");
			try {
				AssortedMethods.info();
			} catch (InterruptedException e1) { log.append("\nInformation Not Exported: InterruptedException");
			} catch (IOException e1) { log.append("\nInformation Not Exported: IOException"); }
		}
		if (eventSource == clear){
			InfoMethods.clear();
		}
		if (eventSource == defaultCarat){
			ConsoleIf.append(computername + "~ $ View>Snap to Bottom");
			InfoMethods.defaultCarat();
		}
		// Viewswitch Needs Work
		if (eventSource == viewswitch){
			ConsoleIf.append(computername + "~ $ View>Switch View");
			InfoMethods.viewswitch();
		}
		if (eventSource == del){
			ConsoleIf.append(computername + "~ $ View>Delete iUtilities Files");
			try {
				AssortedMethods.delete();
			} catch (IOException e1) { log.append("\nDeletion Failed: IOException"); }
		}
		if (eventSource == term){
			ConsoleIf.append(computername + "~ $ Commands>Terminate Process");
			AssortedMethods.terminate();
		}
		if (eventSource == about) {
			ConsoleIf.append(computername + "~ $ Help>About");
			InfoMethods.about();
		}
		if (eventSource == help){
			ConsoleIf.append(computername + "~ $ Help>Help");
			try {
				CoreMethods.help();
			} catch (IOException e1) { log.append("\nHelp Invocation Failed: IOException"); }
		}
		if (eventSource == changelog){
			ConsoleIf.append(computername + "~ $ changelog");
			try {
				InfoMethods.changelog();
			} catch (IOException e1) { log.append("\nChangelog Invocation Failed: IOException"); }
		}
		if (eventSource == updates){
			ConsoleIf.append(computername + "~ $ Help>Updates");
			try {
				AssortedMethods.update();
			} catch (IOException e1) { log.append("\niUtilities Update FAILED: IOException"); }
		}
	}
	
	// To Detect and Disable on Windows.
	public static boolean isWindows() {
		String OS = System.getProperty("os.name");
		return (OS.indexOf("win") >= 0);
	}
}
