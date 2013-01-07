package com.git.ifly6;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSeparator;

public class Console extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console frame = new Console();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Console() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel consolePanel = new JPanel();
		tabbedPane.addTab("Console\n", null, consolePanel, null);
		consolePanel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		consolePanel.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		consolePanel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel logPanel = new JPanel();
		tabbedPane.addTab("Log\n", null, logPanel, null);
		
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);
		
		JMenuItem mntmOutputFile = new JMenuItem("Output File");
		mnNew.add(mntmOutputFile);
		mntmOutputFile.addActionListener(this);
		
		JMenuItem mntmLogFile = new JMenuItem("Log File");
		mnNew.add(mntmLogFile);
		
		JMenuItem mntmDownloadMindterm = new JMenuItem("Download Mindterm");
		mnFile.add(mntmDownloadMindterm);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmShowLog = new JMenuItem("Show Log");
		mnView.add(mntmShowLog);
		
		JMenuItem mntmShowIutilitiesFolder = new JMenuItem("Show iUtilities Folder");
		mnView.add(mntmShowIutilitiesFolder);
		
		JMenuItem mntmShowWorkingDir = new JMenuItem("Show Working DIR");
		mnView.add(mntmShowWorkingDir);
		
		JSeparator separator = new JSeparator();
		mnView.add(separator);
		
		JMenuItem mntmResetDefaults = new JMenuItem("Reset Defaults");
		mnView.add(mntmResetDefaults);
		
		JMenu mnLookAndFeel = new JMenu("Look and Feel");
		mnView.add(mnLookAndFeel);
		
		JMenuItem mntmSystem = new JMenuItem("System");
		mnLookAndFeel.add(mntmSystem);
		
		JMenuItem mntmMetal = new JMenuItem("Metal");
		mnLookAndFeel.add(mntmMetal);
		
		JMenuItem mntmNimbus = new JMenuItem("Nimbus");
		mnLookAndFeel.add(mntmNimbus);
		
		JMenuItem mntmMotif = new JMenuItem("Motif");
		mnLookAndFeel.add(mntmMotif);
		
		JMenu mnCommands = new JMenu("Commands");
		menuBar.add(mnCommands);
		
		JMenuItem mntmSystemInformation = new JMenuItem("System Information");
		mnCommands.add(mntmSystemInformation);
		
		JMenuItem mntmInterfaceConfiguration = new JMenuItem("Interface Configuration");
		mnCommands.add(mntmInterfaceConfiguration);
		
		JSeparator separator_3 = new JSeparator();
		mnCommands.add(separator_3);
		
		JMenuItem mntmAirportToggle = new JMenuItem("Airport Toggle");
		mnCommands.add(mntmAirportToggle);
		
		JMenuItem mntmTerminateRunningProcess = new JMenuItem("Terminate Running Process");
		mnCommands.add(mntmTerminateRunningProcess);
		
		JMenuItem mntmTerminateProcess = new JMenuItem("Terminate Process...");
		mnCommands.add(mntmTerminateProcess);
		
		JSeparator separator_4 = new JSeparator();
		mnCommands.add(separator_4);
		
		JMenuItem mntmTerminateFrivilousProcesses = new JMenuItem("Terminate Frivilous Processes");
		mnCommands.add(mntmTerminateFrivilousProcesses);
		
		JMenuItem mntmPurge = new JMenuItem("Purge");
		mnCommands.add(mntmPurge);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmIutilitiesHelp = new JMenuItem("iUtilities Help");
		mnHelp.add(mntmIutilitiesHelp);
		
		JMenuItem mntmAcknowledgements = new JMenuItem("Acknowledgements");
		mnHelp.add(mntmAcknowledgements);
		
		JSeparator separator_2 = new JSeparator();
		mnHelp.add(separator_2);
		
		JMenuItem mntmChangelog = new JMenuItem("Changelog");
		mnHelp.add(mntmChangelog);
		
		JMenuItem mntmUpdates = new JMenuItem("Updates");
		mnHelp.add(mntmUpdates);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
