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
	public static JTextArea output = new JTextArea();
	public static JTextArea log = new JTextArea();
	public static JTextField input = new JTextField();
	JScrollPane scpOut = new JScrollPane(output);
	JScrollPane scpLog = new JScrollPane(log);

	// INTERNAL DATA
	public static String preoperand;
	public static String[] operand;
	static String[] mem = new String[10];
	static final String starter = "== WinUtilities Console " + Info.version + " == " + 
			"\nHello " + System.getProperty("user.name") + "!" + 
			"\nType 'help' for help.";
	Font font = new Font("Courier", 0, 11);

	JMenuBar menubar = new JMenuBar();
	JMenu menufile = new JMenu("File");
	JMenu menucomm = new JMenu("Commands");
	JMenu menuview = new JMenu("View");
	JMenu menuhelp = new JMenu("Help");
	JMenuItem exportOut = new JMenuItem("Export Output");
	JMenuItem exportLog = new JMenuItem("Export Output");
	JMenuItem info = new JMenuItem("System Readout");
	JMenuItem clear = new JMenuItem("Clear Screen");
	JMenuItem defaultCarat = new JMenuItem("Snap to Bottom");
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
		frame.add(menubar, BorderLayout.NORTH);
		frame.add(tabbedPane, BorderLayout.CENTER);
		menuBarInit();
		
		tabbedPane.addTab("Console", null, consoleTab, null);
		tabbedPane.addTab("Log", null, loggingTab, null);

		// Console Tab Settings
		consoleTab.setLayout(new BorderLayout());
		consoleTab.add(scpOut, BorderLayout.CENTER);
		input.setToolTipText("Type Commands Here");
		input.addKeyListener(this);
		consoleTab.add(input, BorderLayout.SOUTH);

		output.setEditable(false);
		output.setFont(font);
		input.setFont(font);
		DefaultCaret caretOut = (DefaultCaret)output.getCaret();
		caretOut.setUpdatePolicy(2);

		// Logging Tab Settings
		loggingTab.setLayout(new BorderLayout());
		loggingTab.add(scpLog, BorderLayout.CENTER);
		log.setEditable(false);
		log.setFont(font);
		DefaultCaret caretLog = (DefaultCaret)output.getCaret();
		caretLog.setUpdatePolicy(2);
		
		frame.setVisible(true);
		log.append("\nJava Swing GUI Initialised and Rendered");
	}
	private void menuBarInit(){
		// MENUBAR CREATION
		menubar.add(menufile);
		menubar.add(menucomm);
		menubar.add(menuview);
		menubar.add(menuhelp);

		// File
		menufile.add(exportOut);
		menufile.add(exportLog);
		exportOut.addActionListener(this);
		exportLog.addActionListener(this);
		// Commands
		menucomm.add(info);
		menucomm.add(term);
		info.addActionListener(this);
		term.addActionListener(this);
		// View
		menuview.add(clear);
		menuview.add(defaultCarat);
		menuview.add(del);
		clear.addActionListener(this);
		defaultCarat.addActionListener(this);
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
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Console();
				} catch (Exception e) { System.out.println("CRITICAL FAILURE"); }
			}
		});

		// Visible Housekeeping
		output.append(starter);
		computername = InetAddress.getLocalHost().getHostName();
		Date date = new Date();
		log.append("WinUtilities " + Info.version + " Initialised. Date: " + date);
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
		if (eventSource == exportOut) {
			output.append("\n" + computername + "~ $ File>Export Output");
			try {
				InOutMethods.save();
			} catch (IOException e1) { log.append("\nExport Failed, IOException"); }
		}
		if (eventSource == exportLog) {
			ConsoleIf.append("\n" + computername + "~ $ File>Export Log");
			try {
				InOutMethods.saveLog();
			} catch (IOException e1) { log.append("\nLog JTextArea Export Failed: IOException"); }
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
