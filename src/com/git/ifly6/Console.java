package com.git.ifly6;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultEditorKit;

/**
 * Main Class for Utilities Pro 3.x
 * 
 * @author ifly6
 * @version 3.x
 */
public class Console {

	/*
	 * Naming Conventions: System is: <major>.<minor>_<revision> or:
	 * <major>.<minor>_dev<#> Exempli Gratia: 2.2_01 = Major Version 2, Minor
	 * Version 2, 1 Revision. Exempli Gratia: 3.0_dev04 = Major Version 3, Minor
	 * Version 0, Development Version 4
	 * 
	 * The 2.x Versions: 2.0 = greentree 2.1 = greenwell 2.2 = greenmont 2.3 =
	 * greenhill 2.4 = greenfield 2.5 = greenfall 2.6 = greenpool 2.7 =
	 * greenberg 2.8 = greenland
	 * 
	 * The 3.x Versions 3.0 = iceland 3.1 = iceberg 3.2 = icepool 3.3 = skyfall
	 * 3.4 = icefield 3.5 = everest 3.6 = icemont 3.7 = icewell 3.8 = icedtea
	 */

	private JFrame frame;
	public static String version = "3.0_dev04";
	public static String keyword = "iceland";

	/**
	 * @param userName
	 *            UserName for the User (used for opening greeting)
	 * @param UtilitiesPro_DIR
	 *            Folder Location in Application Support for programme
	 * @param Downloads_DIR
	 *            Folder Location for Downloads
	 * @param rt
	 *            Runtime reference. Should be removed, but it is not deprecated
	 * @param outText
	 *            logText See JavaDoc before GUI initialisation
	 * @param Process
	 *            used to make something to execute or destroy processes
	 */
	public static String userName = System.getProperty("user.name");
	public static String UtilitiesPro_DIR = "/Users/" + userName
			+ "/Library/Application Support/Utilities Pro";
	public static String Downloads_DIR = "/Users/" + userName + "/Downloads/";
	public static Runtime rt = Runtime.getRuntime();
	private static JTextArea outText;
	private static JTextArea logText;
	private static TextField inputField;
	public static Process process;

	/**
	 * Launch the application.
	 * 
	 * @param inputArgs
	 *            When launched from command line, the programme will update
	 *            Utilities Pro.
	 */
	public static void main(String[] inputArgs) {

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				"Utilities Pro");

		FileReader configRead = null;
		String look = "Default";
		try {
			configRead = new FileReader(UtilitiesPro_DIR + "/config");
			Scanner scan = new Scanner(configRead);
			look = scan.nextLine();
		} catch (FileNotFoundException e1) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (UnsupportedLookAndFeelException e) {
			}
		}

		// GUI Look and Feel
		if (look.equals("CrossPlatformLAF")) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (UnsupportedLookAndFeelException e) {
			}
		} else {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (UnsupportedLookAndFeelException e) {
			}
		}

		if ("--update".equals(inputArgs[0]) || "-u".equals(inputArgs[0])) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					log("Utilities Pro Update Triggered");
					String[] url = { "curl", "-o", Downloads_DIR,
							"http://ifly6.no-ip.org/UtilitiesPro/UtilitiesPro-latest.jar" };
					try {
						rt.exec(url);
					} catch (IOException e) {
						log("Utilities Pro Download Failed");
					}
					append("Utilities Pro Updated. File in ~/Downloads.");
				}
			});
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Console window = new Console();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Console() {
		initialize();
	}

	/**
	 * TODO Add CD System (again, coming from the last time). This system starts
	 * the main GUI for the programme.
	 * 
	 * @param frame
	 *            JFrame for the programme
	 * @param panel
	 *            Panel for the Console's Tab
	 * @param scrollPane_logText
	 *            Pane for the Logging Tab
	 * @param outText
	 *            JTextArea for the Console's output
	 * @param logText
	 *            JTextArea for the Logging's output
	 * @param inputField
	 *            TextField (AWT) for input into the programme
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == 10) {
					TextCommands.process();
				}
			}
		});
		frame.setBounds(0, 0, 670, 735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Utilities Pro" + version);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Console", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		inputField = new TextField();
		inputField.setFont(new Font("Monaco", Font.PLAIN, 12));
		panel.add(inputField, BorderLayout.SOUTH);

		outText = new JTextArea();
		outText.setEditable(false);
		outText.setFont(new Font("Monaco", Font.PLAIN, 12));
		JScrollPane scrollPane_outPane = new JScrollPane(outText);
		panel.add(scrollPane_outPane, BorderLayout.CENTER);

		logText = new JTextArea();
		logText.setEditable(false);
		logText.setFont(new Font("Monaco", Font.PLAIN, 12));
		JScrollPane scrollPane_logText = new JScrollPane(logText);
		tabbedPane.addTab("Log", null, scrollPane_logText,
				"Shows a dynamic log of all functions run.");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenConfig = new JMenuItem("Open Configuration Folder");
		mntmOpenConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileCommands.OpenConfig();
			}
		});
		mnFile.add(mntmOpenConfig);

		JMenuItem mntmDeleteConfig = new JMenuItem("Delete Configuration");
		mntmDeleteConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileCommands.DeleteConfig();
			}
		});
		mnFile.add(mntmDeleteConfig);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExportConsole = new JMenuItem("Export Console");
		mntmExportConsole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileCommands.export(1);
			}
		});
		mnFile.add(mntmExportConsole);

		JMenuItem mntmConsoleLog = new JMenuItem("Export Log");
		mntmConsoleLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileCommands.export(2);
			}
		});
		mnFile.add(mntmConsoleLog);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mntmCut.setText("Cut");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.META_MASK));
		mnEdit.add(mntmCut);

		JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		mntmCopy.setText("Copy");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.META_MASK));
		mnEdit.add(mntmCopy);

		JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mntmPaste.setText("Paste");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.META_MASK));
		mnEdit.add(mntmPaste);

		JSeparator separator_4 = new JSeparator();
		mnEdit.add(separator_4);

		JMenuItem mntmClearConsole = new JMenuItem("Clear Console");
		mntmClearConsole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditCommands.consoleClear();
			}
		});
		mnEdit.add(mntmClearConsole);

		JMenuItem mntmClearLog = new JMenuItem("Clear Log");
		mntmClearLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditCommands.logClear();
			}
		});
		mnEdit.add(mntmClearLog);

		JMenu mnScripts = new JMenu("Scripts");
		menuBar.add(mnScripts);

		JMenuItem mntmPurge = new JMenuItem("Purge Memory");
		mntmPurge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScriptCommands.purge();
			}
		});
		mnScripts.add(mntmPurge);

		JMenuItem mntmRestartAirport = new JMenuItem("Restart Airport");
		mntmRestartAirport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScriptCommands.wireless();
			}
		});
		mnScripts.add(mntmRestartAirport);

		JSeparator separator_1 = new JSeparator();
		mnScripts.add(separator_1);

		JMenuItem mntmSystemInfo = new JMenuItem("System Information");
		mntmSystemInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScriptCommands.readout();
			}
		});
		mnScripts.add(mntmSystemInfo);

		JMenuItem mntmDownloadMindterm = new JMenuItem("Download Mindterm");
		mntmDownloadMindterm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScriptCommands.mindterm();
			}
		});
		mnScripts.add(mntmDownloadMindterm);

		JMenu mnCommand = new JMenu("Command");
		menuBar.add(mnCommand);

		JMenuItem mntmTerminateUtilitiesPro = new JMenuItem(
				"Terminate Utilities Pro Process");
		mntmTerminateUtilitiesPro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandCommands.terminateUtility();
			}
		});
		mnCommand.add(mntmTerminateUtilitiesPro);

		JMenuItem mntmTerminateArbitraryProcess = new JMenuItem(
				"Terminate Arbitrary Process");
		mntmTerminateArbitraryProcess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandCommands.terminate();
			}
		});
		mnCommand.add(mntmTerminateArbitraryProcess);

		JSeparator separator_3 = new JSeparator();
		mnCommand.add(separator_3);

		JMenuItem mntmAboutCommandLine = new JMenuItem("About Command Line");
		mnCommand.add(mntmAboutCommandLine);

		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		JMenuItem mntmHelp = new JMenuItem("Utilities Pro Help");
		mnHelp.add(mntmHelp);

		JSeparator separator_2 = new JSeparator();
		mnHelp.add(separator_2);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnHelp.add(mntmQuit);
	}

	/**
	 * @since 2.2_01
	 * @param in
	 *            String to append into the JTextArea outText
	 */
	public static void append(String in) {

		outText.append("\n" + in);
	}

	/**
	 * @since 2.2_02
	 * @param in
	 *            String to append (with a space) into the JTextArea outText
	 */
	public static void out(String in) {
		outText.append("\n " + in);
	}

	/**
	 * @since 2.2_01
	 * @param in
	 *            String to append (with a space) into the JTextArea logText
	 */
	public static void log(String in) {
		logText.append("\n" + new Date() + " " + in);
	}

	public static String getOutText() {
		return outText.getText();
	}

	public static String getLogText() {
		return logText.getText();
	}

	/**
	 * As it deals with the GUI's implementation (JTextArea), Java forces its
	 * location to be inside the GUI's declaration class.
	 * 
	 * @author ifly6
	 * @since 3.0
	 * @param which
	 *            integer value, determines which JTextArea to clear (1,
	 *            outText; 2, logText)
	 */
	public static void clearText(int which) {
		if (which == 1) {
			outText.setText(null);
		}
		if (which == 2) {
			logText.setText(null);
		}
	}

	/**
	 * Used to create (if necessary) all folders for Utilities Pro
	 * 
	 * @author ifly6
	 * @since 3.0
	 */
	public static void mkdir() {
		File folder = new File(UtilitiesPro_DIR);
		folder.mkdirs();
		folder = new File(Downloads_DIR);
		folder.mkdirs();
	}

	public static String getInputField() {
		String a = inputField.getText();
		inputField.setText(null);
		return a;
	}
}
