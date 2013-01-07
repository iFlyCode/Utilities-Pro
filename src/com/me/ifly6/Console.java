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
import com.me.ifly6.Parametres;

public class Console extends JFrame implements KeyListener, ActionListener{
	// Name: Console (GUI Class)

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
	JFrame frame = new JFrame("iUtilities " + Parametres.version);

	public static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public static JPanel consoleTab = new JPanel();
	public static JPanel loggingTab = new JPanel();
	public static JTextArea display = new JTextArea();
	public static JTextArea log = new JTextArea();
	public static JTextField input = new JTextField();
	JScrollPane scp_tab1 = new JScrollPane(display);
	JScrollPane scp_tab2 = new JScrollPane(log);

	// INTERNAL DATA
	public static String preoperand;
	public static String[] operand;
	static String[] mem = new String[10];
	public static Font font = new Font("Monaco", 0, 11);

	JMenuBar menubar = new JMenuBar();
	JMenu menufile = new JMenu("File");
	JMenu menucomm = new JMenu("Commands");
	JMenu menuview = new JMenu("View");
	JMenu menuhelp = new JMenu("Help");
	JMenuItem del = new JMenuItem("Delete iUtilities Files");
	JMenuItem export = new JMenuItem("Exportation");
	JMenuItem script = new JMenuItem("Script Input");
	JMenuItem mindterm = new JMenuItem("Mindterm");
	JMenuItem purge = new JMenuItem("Inactive Memory Purge");
	JMenuItem debug = new JMenuItem("Log Console");
	JMenuItem info = new JMenuItem("System Readout");
	JMenuItem clear = new JMenuItem("Clear Screen");
	JMenuItem defaultCarat = new JMenuItem("Snap to Bottom");
	JMenuItem newConsole = new JMenuItem("New Console Window");
	JMenuItem logEnable = new JMenuItem("Enable Log View");
	JMenu lookAndFeel = new JMenu("Look and Feel");
	JMenuItem metalInf = new JMenuItem("Metal Interface");
	JMenuItem macIntrf = new JMenuItem("System Interface");
	JMenuItem term = new JMenuItem("Terminate Process");
	JMenuItem about = new JMenuItem("About");
	JMenuItem help = new JMenuItem("Help");
	JMenuItem changelog = new JMenuItem("Changelog");
	JMenuItem updates = new JMenuItem("Updates");

	public Console()
	{
		initialise();
		consoleSettings();
		log.append("\nJava Swing GUI Initialised and Rendered");
	}

	public void initialise(){
		// Window and MenuBar
		frame.setBounds(0, 0, 670, 735);
		frame.getContentPane().setLayout(new BorderLayout());
		menubar.setBorderPainted(false);

		menubar.setBackground(Color.LIGHT_GRAY);
		menubar.setForeground(Color.BLACK);
		menufile.setHorizontalAlignment(SwingConstants.LEFT);
		menufile.setBackground(Color.LIGHT_GRAY);
		menufile.setForeground(Color.BLACK);

		// MenuBar.add Menus
		menubar.add(menufile);
		menucomm.setBackground(Color.LIGHT_GRAY);
		menubar.add(menucomm);
		menuview.setBackground(Color.LIGHT_GRAY);
		menubar.add(menuview);
		menuhelp.setBackground(Color.LIGHT_GRAY);
		menubar.add(menuhelp);

		// File
		menufile.add(del);
		menufile.add(export);
		menufile.add(script);
		menufile.add(mindterm);
		del.addActionListener(this);
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
		menuview.add(newConsole);
		menuview.add(logEnable);
		menuview.add(lookAndFeel);
		lookAndFeel.add(metalInf); lookAndFeel.add(macIntrf);
		clear.addActionListener(this);
		defaultCarat.addActionListener(this);
		newConsole.addActionListener(this);
		logEnable.addActionListener(this);
		metalInf.addActionListener(this);
		macIntrf.addActionListener(this);
		// Help
		menuhelp.add(about);
		menuhelp.add(help);
		menuhelp.add(changelog);
		menuhelp.add(updates);
		about.addActionListener(this);
		help.addActionListener(this);
		changelog.addActionListener(this);
		updates.addActionListener(this);

		frame.setJMenuBar(menubar);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void consoleSettings(){
		// Sets the Layout, and Adds things to the Console's Tab.
		consoleTab.setLayout(new BorderLayout());
		consoleTab.add(scp_tab1, BorderLayout.CENTER);
		consoleTab.add(input, BorderLayout.SOUTH);
		input.setFont(font);
		input.addKeyListener(this);

		scp_tab1.setViewportBorder(new LineBorder(Color.WHITE, 6));

		DefaultCaret caret = (DefaultCaret)display.getCaret();
		display.setEditable(false);
		display.setFont(font);
		caret.setUpdatePolicy(2);
		
		tabbedPane.addTab("Console", null, consoleTab, null);
	}

	private void loggingSettings(){
		loggingTab.setLayout(new BorderLayout());
		scp_tab2.setViewportBorder(new LineBorder(Color.WHITE, 6));
		loggingTab.add(scp_tab2, BorderLayout.CENTER);

		log.setEditable(false);
		log.setFont(font);
		
		tabbedPane.addTab("Log", null, loggingTab, null);
	}

	public static void launchGUI(){

		// GUI Construction Call
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Console window = new Console();
					window.frame.setVisible(true);
				} catch (Exception e) { System.out.println("CRITICAL FAILURE"); }
			}
		});

		// OS Restriction
		if (isWindows()){
			log.append("\nWindows Detected. Disengaging.");
			String temp = "Windows Detected. Disengaging to prevent damage," +
					"\nas this is requires UNIX Commands and uses different FS.";
			JOptionPane.showMessageDialog(null, temp, "OS Validation", -1);
			try { Thread.sleep(10000);
			} catch (InterruptedException e) { }
			System.exit(0);
		}

		// Visible Housekeeping
		display.append(Parametres.starter);
		try { computername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) { }
		Date date = new Date();
		log.append("\niUtilities " + Parametres.version + " Initialised. Date: " + date);
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
		if (eventSource == del){
			ConsoleIf.append(computername + "~ $ View>Delete iUtilities Files");
			try {
				InOutMethods.delete();
			} catch (IOException e1) { log.append("\nDeletion Failed: IOException"); }
		}
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
		if (eventSource == newConsole){
			newConsole();
			ConsoleIf.append(computername + "~ $ View>New Console Window");
			ConsoleIf.log("New Console Tab.");
		}
		if (eventSource == logEnable){
			ConsoleIf.append(computername + "~ $ View>Enable Log Tab");
			enableLogTab();
		}
		if (eventSource == metalInf){
			ConsoleIf.append(computername + "~ $ View>Metal Interface");
			GraphicsMethods.saveConfig("CrossPlatformLAF");
		}
		if (eventSource == macIntrf){
			ConsoleIf.append(computername + "~ $ View>System Interface");
			GraphicsMethods.saveConfig("Default");
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
	
	/* ============================
	 * iUTILITIES GRAPHICS METHODS
	 * ============================
	 */
	
	void enableLogTab(){
		loggingSettings();
	}

	void newConsole() {
		// TODO Find some way to add Console Tabs to this thing.
		launchGUI();
	}
}
