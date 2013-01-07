package com.me.ifly6;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.border.LineBorder;

import com.me.ifly6.methods.*;

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
	public static String currentDir = new File(".").getAbsolutePath();

	// SWING DATA
	static JFrame frame = new JFrame("WinUtilities " + Info.version);
	static JTabbedPane tabbedPane = new JTabbedPane();
	JPanel consoleTab = new JPanel();
	JPanel loggingTab = new JPanel();
	public static JTextArea display = new JTextArea();
	public static JTextArea output = new JTextArea();
	public static JTextArea log = new JTextArea();
	public static JTextField input = new JTextField();
	JScrollPane scp = new JScrollPane(display);

	// INTERNAL DATA
	public static String preoperand;
	public static String[] operand;
	static String[] mem = new String[10];
	static final String starter = "== WinUtilities Console " + Info.version + " == " + 
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
	JMenuItem debug = new JMenuItem("Log Console");
	JMenuItem info = new JMenuItem("System Readout");
	JMenuItem clear = new JMenuItem("Clear Screen");
	JMenuItem defaultCarat = new JMenuItem("Snap to Bottom");
	JMenuItem viewswitch = new JMenuItem("Switch View");
	JMenuItem del = new JMenuItem("Delete WinUtilities Files");
	JMenuItem term = new JMenuItem("Terminate Process");
	JMenuItem about = new JMenuItem("About");
	JMenuItem help = new JMenuItem("Help");
	JMenuItem changelog = new JMenuItem("Changelog");
	JMenuItem updates = new JMenuItem("Updates");

	Console()
	{
		// Base GUI, in Swing.
		frame.setBounds(10, 10, 670, 735);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(tabbedPane);
		tabbedPane.add(consoleTab);
		tabbedPane.add(loggingTab);
		scp.setViewportBorder(new LineBorder(Color.white));

		consoleTab.setLayout(new BorderLayout());
		consoleTab.add(scp, BorderLayout.CENTER);
		input.setToolTipText("Type Commands Here");
		consoleTab.add(input, BorderLayout.SOUTH);
		input.addKeyListener(this);

		Font font = new Font("Courier", 0, 11);
		display.setEditable(false);
		display.setFont(font);
		input.setFont(font);
		DefaultCaret caret = (DefaultCaret)display.getCaret();
		caret.setUpdatePolicy(2);

		// MENUBAR CREATION
		menubar.add(menufile);
		menubar.add(menucomm);
		menubar.add(menuview);
		menubar.add(menuhelp);

		// File
		menufile.add(export);
		export.addActionListener(this);
		// Commands
		menucomm.add(debug);
		menucomm.add(info);
		menucomm.add(term);
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

		consoleTab.add(menubar, BorderLayout.NORTH);
		frame.setVisible(true);
		log.append("\nJava Swing GUI Initialised and Rendered");
	}

	// MAIN THREAD.
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		
		// GUI Construction
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) { 
		} catch (UnsupportedLookAndFeelException e) {}
		new Console();

		// Visible Housekeeping
		display.append(starter);
		computername = InetAddress.getLocalHost().getHostName();
		Date date = new Date();
		log.append("\nWinUtilities " + Info.version + " Initialised. Date: " + date);
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
				InOutMethods.save();
			} catch (IOException e1) { log.append("\nExport Failed, IOException"); }
		}
		if (eventSource == debug) {
			ConsoleIf.append("\n" + computername + "~ $ Command>Debug");
			try {
				InOutMethods.saveLog();
			} catch (IOException e1) { log.append("\nBug JTextArea Export Failed: IOException"); }
		}
		if (eventSource == info){
			ConsoleIf.append(computername + "~ $ Command>System Readout");
			try {
				InfoMethods.info();
			} catch (InterruptedException e1) { log.append("\nInformation Not Exported: InterruptedException");
			} catch (IOException e1) { log.append("\nInformation Not Exported: IOException"); }
		}
		if (eventSource == clear){
			GraphicsMethods.clear();
		}
		if (eventSource == defaultCarat){
			ConsoleIf.append(computername + "~ $ View>Snap to Bottom");
			GraphicsMethods.defaultCarat();
		}
		if (eventSource == del){
			ConsoleIf.append(computername + "~ $ View>Delete WinUtilities Files");
			try {
				InOutMethods.delete();
			} catch (IOException e1) { log.append("\nDeletion Failed: IOException"); }
		}
		if (eventSource == term){
			ConsoleIf.append(computername + "~ $ Commands>Terminate Process");
			InOutMethods.terminate();
		}
		if (eventSource == about) {
			ConsoleIf.append(computername + "~ $ Help>About");
			InfoMethods.about();
		}
		if (eventSource == help){
			ConsoleIf.append(computername + "~ $ Help>Help");
			try {
				CoreMethods.helpList();
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
				InOutMethods.update();
			} catch (IOException e1) { log.append("\nWinUtilities Update FAILED: IOException"); }
		}
	}
}
