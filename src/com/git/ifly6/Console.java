package com.git.ifly6;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.TextField;
import javax.swing.JTextArea;
import javax.swing.text.DefaultEditorKit;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Console {

	/***
	 * @author Kevin Wong, ifly6
	 * @version v3.x
	 */

	private JFrame frame;
	public static String version = "3.0_dev01";
	
	// Dependencies
	public static String userName = System.getProperty("user.name");
	public static String UtilitiesPro_DIR = "/Users/" + userName + "/Library/Application Support/Utilities Pro";
	public static String Downloads_DIR = "/Users/" + userName + "/Downloads/";
	public static Runtime rt = Runtime.getRuntime();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Utilities Pro");

		FileReader configRead = null;
		String look = "Default";
		try {
			configRead = new FileReader(UtilitiesPro_DIR + "/config");
			Scanner scan = new Scanner(configRead);
			look = scan.nextLine();
		} catch (FileNotFoundException e1) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) { 
			} catch (UnsupportedLookAndFeelException e) {}
		}

		// GUI Look and Feel
		if (look.equals("CrossPlatformLAF")){
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) { 
			} catch (UnsupportedLookAndFeelException e) {}
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) { 
			} catch (UnsupportedLookAndFeelException e) {}
		}

		EventQueue.invokeLater(new Runnable() {
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
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 670, 735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Utilities Pro" + version);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Console", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		TextField inputField = new TextField();
		inputField.setFont(new Font("Monaco", Font.PLAIN, 12));
		panel.add(inputField, BorderLayout.SOUTH);

		JTextArea outText = new JTextArea();
		outText.setEditable(false);
		outText.setFont(new Font("Monaco", Font.PLAIN, 12));
		JScrollPane scrollPane_outPane = new JScrollPane(outText);
		panel.add(scrollPane_outPane, BorderLayout.CENTER);

		JTextArea logText = new JTextArea();
		logText.setEditable(false);
		logText.setFont(new Font("Monaco", Font.PLAIN, 12));
		JScrollPane scrollPane_logText = new JScrollPane(logText);
		tabbedPane.addTab("Log", null, scrollPane_logText, "Shows a dynamic log of all functions run.");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenConfig = new JMenuItem("Open Configuration Folder");
		mntmOpenConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileCommands.OpenConfig();
			}
		});
		mnFile.add(mntmOpenConfig);

		JMenuItem mntmDeleteConfig = new JMenuItem("Delete Configuration");
		mntmDeleteConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileCommands.DeleteConfig();
			}
		});
		mnFile.add(mntmDeleteConfig);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmExportConsole = new JMenuItem("Export Console");
		mntmExportConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileCommands.exportOutput();
			}
		});
		mnFile.add(mntmExportConsole);

		JMenuItem mntmConsoleLog = new JMenuItem("Export Log");
		mntmConsoleLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileCommands.exportLog();
			}
		});
		mnFile.add(mntmConsoleLog);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mntmCut.setText("Cut");
		mntmCut.setAccelerator(
			      KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.META_MASK));
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		mntmCopy.setText("Copy");
		mntmCopy.setAccelerator(
			      KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mntmPaste.setText("Paste");
		mntmPaste.setAccelerator(
			      KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.META_MASK));
		mnEdit.add(mntmPaste);
		
		JSeparator separator_4 = new JSeparator();
		mnEdit.add(separator_4);

		JMenuItem mntmClearConsole = new JMenuItem("Clear Console");
		mnEdit.add(mntmClearConsole);

		JMenuItem mntmClearLog = new JMenuItem("Clear Log");
		mnEdit.add(mntmClearLog);

		JMenu mnScripts = new JMenu("Scripts");
		menuBar.add(mnScripts);

		JMenuItem mntmPurge = new JMenuItem("Purge Memory");
		mnScripts.add(mntmPurge);

		JMenuItem mntmRestartAirport = new JMenuItem("Restart Airport");
		mnScripts.add(mntmRestartAirport);

		JSeparator separator_1 = new JSeparator();
		mnScripts.add(separator_1);

		JMenuItem mntmSystemInfo = new JMenuItem("System Information");
		mnScripts.add(mntmSystemInfo);

		JMenuItem mntmDownloadMindterm = new JMenuItem("Download Mindterm");
		mnScripts.add(mntmDownloadMindterm);

		JMenu mnCommand = new JMenu("Command");
		menuBar.add(mnCommand);

		JMenuItem mntmTerminateUtilitiesPro = new JMenuItem("Terminate Utilities Pro Process");
		mnCommand.add(mntmTerminateUtilitiesPro);

		JMenuItem mntmTerminateArbitraryProcess = new JMenuItem("Terminate Arbitrary Process");
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
}
