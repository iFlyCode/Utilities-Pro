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
	static JFrame frame = new JFrame("iUtilities " + Info.version);

	public static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public static JPanel tab1 = new JPanel();
	public static JPanel tab2 = new JPanel();
	public static JTextArea display = new JTextArea();
	public static JTextArea log = new JTextArea();
	public static JTextField input = new JTextField();
	JScrollPane scp_tab1 = new JScrollPane(display);
	JScrollPane scp_tab2 = new JScrollPane(log);

	// INTERNAL DATA
	public static String preoperand;
	public static String[] operand;
	static String[] mem = new String[10];
	static final String starter = "== iUtilities Console " + Info.version + " == " + 
			"\nHello " + System.getProperty("user.name") + "!" + 
			"\nType '/help' for help.";
	public static int screen_state = 0;
	public static String screen_stored = starter;
	public static Font font = new Font("Monaco", 0, 11);

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
	JMenuItem del = new JMenuItem("Delete iUtilities Files");
	JMenuItem logEnable = new JMenuItem("Enable Log View");
	JMenuItem term = new JMenuItem("Terminate Process");
	JMenuItem about = new JMenuItem("About");
	JMenuItem help = new JMenuItem("Help");
	JMenuItem changelog = new JMenuItem("Changelog");
	JMenuItem updates = new JMenuItem("Updates");

	Console()
	{
		// Base GUI, in Swing.
		frame.setBounds(50, 50, 670, 735);
		frame.getContentPane().setLayout(new BorderLayout());
		menubar.setBorderPainted(false);
		frame.getContentPane().add(menubar, BorderLayout.NORTH);
		
		menubar.setBackground(Color.LIGHT_GRAY);
		menubar.setForeground(Color.BLACK);
		menufile.setHorizontalAlignment(SwingConstants.LEFT);
		menufile.setBackground(Color.LIGHT_GRAY);
		menufile.setForeground(Color.BLACK);
		
				// MENUBAR CREATION
				menubar.add(menufile);
				menucomm.setBackground(Color.LIGHT_GRAY);
				menubar.add(menucomm);
				menuview.setBackground(Color.LIGHT_GRAY);
				menubar.add(menuview);
				menuhelp.setBackground(Color.LIGHT_GRAY);
				menubar.add(menuhelp);
				
						// File
						menufile.add(export);
						menufile.add(script);
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
						menuview.add(del);
						menuview.add(logEnable);
						clear.addActionListener(this);
						defaultCarat.addActionListener(this);
						del.addActionListener(this);
						logEnable.addActionListener(this);
						// Help
						menuhelp.add(about);
						menuhelp.add(help);
						menuhelp.add(changelog);
						menuhelp.add(updates);
						about.addActionListener(this);
						help.addActionListener(this);
						changelog.addActionListener(this);
						updates.addActionListener(this);
						
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tabbedPane.addTab("Console", null, tab1, null);

		tab1.setLayout(new BorderLayout());
		tab2.setLayout(new BorderLayout());
		scp_tab1.setViewportBorder(new LineBorder(Color.WHITE, 6));
		scp_tab2.setViewportBorder(new LineBorder(Color.WHITE, 6));

		tab1.add(scp_tab1, BorderLayout.CENTER);
		tab1.add(input, BorderLayout.SOUTH);
		tab2.add(scp_tab2, BorderLayout.CENTER);

		input.addKeyListener(this);

		// GUI Colours
		input.setFont(font);
		DefaultCaret caret = (DefaultCaret)display.getCaret();
		display.setEditable(false);
		display.setFont(font);
		log.setEditable(false);
		log.setFont(font);

		// Set Visibles.
		caret.setUpdatePolicy(2);
		frame.setVisible(true);
		log.append("\nJava Swing GUI Initialised and Rendered");
	}

	// MAIN THREAD.
	public static void main(String[] args) throws UnknownHostException, InterruptedException {

		// GUI Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) { 
		} catch (UnsupportedLookAndFeelException e) {}

		// GUI Construction Call
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Console();
				} catch (Exception e) { System.out.println("CRITICAL FAILURE");
				}
			}
		});

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
				InOutMethods.save();
			} catch (IOException e1) { log.append("\nExport Failed, IOException"); }
		}
		if (eventSource == script) {
			display.append("\n" + computername + "~ $ File>Script");
			InOutMethods.script();
		}
		if (eventSource == mindterm) {
			display.append("\n" + computername + "~ $ File>Mindterm");
			try {
				InOutMethods.mindterm();
			} catch (IOException e1) { log.append("\nMindterm Download Failed: IOException"); }
		}
		if (eventSource == purge) {
			display.append("\n" + computername + "~ $ Command>Purge");
			try {
				InOutMethods.purge();
			} catch (IOException e1) { log.append("\nPurge Failed: IOException");}
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
			ConsoleIf.append(computername + "~ $ View>Delete iUtilities Files");
			try {
				InOutMethods.delete();
			} catch (IOException e1) { log.append("\nDeletion Failed: IOException"); }
		}
		if (eventSource == logEnable){
			GraphicsMethods.enableLogTab();
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
			} catch (IOException e1) { log.append("\niUtilities Update FAILED: IOException"); }
		}
	}

	// To Detect whether on Windows.
	public static boolean isWindows() {
		String OS = System.getProperty("os.name");
		return (OS.indexOf("win") >= 0);
	}
}
