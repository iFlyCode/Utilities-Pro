package ifly6.UtilitiesPro2.copy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

import ifly6.UtilitiesPro2.methods.CoreMethods;
import ifly6.UtilitiesPro2.methods.GraphicsMethods;
import ifly6.UtilitiesPro2.methods.InOutMethods;
import ifly6.UtilitiesPro2.methods.InfoMethods;

/**
 * TODO IMPLEMENT A CHANGE DIRECTORY SYSTEM.
 *
 * @since 1.0
 * @author ifly6, mudkip1123, DYNAbeast
 * @deprecated THIS ENTIRE PACKAGE (and com.me.ifly6.methods) is deprecated.
 */
@Deprecated
public class Console extends JFrame implements KeyListener, ActionListener {
	// Name: Console (GUI Class)

	private static final long serialVersionUID = 1L;

	// EXTERNAL DATA
	protected static String computername = "Unknown";
	protected static int numArray = 20;
	protected static String currentDir = new File(".").getAbsolutePath();

	// SWING DATA
	JFrame frame = new JFrame("Utilities Pro " + Parametres.version);

	public static JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	public static JPanel consoleTab = new JPanel();
	public static JPanel loggingTab = new JPanel();
	public static JTextArea display = new JTextArea();
	public static JTextArea log = new JTextArea();
	public static JTextField input = new JTextField();
	JScrollPane scpConsole = new JScrollPane(display);
	JScrollPane scpLogging = new JScrollPane(log);

	// INTERNAL DATA
	protected static String preoperand;
	protected static String[] operand;
	protected static String[] mem = new String[10];
	protected static Font font = new Font("Monaco", 0, 11);

	JMenuBar menubar = new JMenuBar();
	JMenu menufile = new JMenu("File");
	JMenu menucomm = new JMenu("Commands");
	JMenu menuview = new JMenu("View");
	JMenu menuhelp = new JMenu("Help");
	JMenuItem del = new JMenuItem("Delete Utilities Pro Files");
	JMenuItem export = new JMenuItem("Exportation");
	JMenuItem script = new JMenuItem("Script Input");
	JMenuItem mindterm = new JMenuItem("Mindterm");
	JMenuItem purge = new JMenuItem("Inactive Memory Purge");
	JMenuItem debug = new JMenuItem("Log Export");
	JMenuItem info = new JMenuItem("System Readout");
	JMenuItem clear = new JMenuItem("Clear Screen");
	JMenuItem defaultCarat = new JMenuItem("Snap to Bottom");
	JMenuItem logEnable = new JMenuItem("Enable Log View");
	JMenu lookAndFeel = new JMenu("Look and Feel");
	JMenuItem metalInf = new JMenuItem("Metal Interface");
	JMenuItem macIntrf = new JMenuItem("System Interface");
	JMenuItem term = new JMenuItem("Terminate Process");
	JMenuItem about = new JMenuItem("About");
	JMenuItem help = new JMenuItem("Help");
	JMenuItem changelog = new JMenuItem("Changelog");
	JMenuItem updates = new JMenuItem("Updates");

	Console() {
		initialise();
		consoleSettings();
		log.append("\nSwing GUI Initialised and Rendered");
	}

	protected void initialise() {
		// Window and MenuBar
		frame.setBounds(0, 0, 670, 735);
		frame.getContentPane().setLayout(new BorderLayout());
		menubar.setBorderPainted(false);

		menubar.setBackground(Color.LIGHT_GRAY);
		menubar.setForeground(Color.BLACK);

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
		menuview.add(logEnable);
		menuview.add(lookAndFeel);
		lookAndFeel.add(metalInf);
		lookAndFeel.add(macIntrf);
		clear.addActionListener(this);
		defaultCarat.addActionListener(this);
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

	private void consoleSettings() {
		// Sets the Layout, and Adds things to the Console's Tab.
		consoleTab.setLayout(new BorderLayout());
		consoleTab.add(scpConsole, BorderLayout.CENTER);
		consoleTab.add(input, BorderLayout.SOUTH);
		input.setFont(font);
		input.addKeyListener(this);

		scpConsole.setViewportBorder(new LineBorder(Color.WHITE, 6));

		DefaultCaret caret = (DefaultCaret) display.getCaret();
		display.setEditable(false);
		display.setFont(font);
		caret.setUpdatePolicy(2);

		tabbedPane.addTab("Console", null, consoleTab, null);
	}

	private void loggingSettings() {
		loggingTab.setLayout(new BorderLayout());
		scpLogging.setViewportBorder(new LineBorder(Color.WHITE, 6));
		loggingTab.add(scpLogging, BorderLayout.CENTER);

		log.setEditable(false);
		log.setFont(font);

		tabbedPane.addTab("Log", null, loggingTab, null);
	}

	public static void launchGUI() {

		// GUI Construction Call
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Console window = new Console();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println("CRITICAL FAILURE");
				}
			}
		});

		// Visible Housekeeping
		display.append(Parametres.starter);
		try {
			computername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
		}
		log.append("\nUtilities Pro " + Parametres.version + " Initialised. Date: " + new Date());
	}

	// EVENT HANDLER
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == 10) {
			try {
				TextProc.proc();
			} catch (IOException e1) {
				log.append("\nkeyPressed Error, IO Exception");
			} catch (InterruptedException e1) {
				log.append("\nkeyPressed Error, InterruptedException");
			}
		}
		if (keyCode == 38) {
			// TODO Memory for Commands when pressing UP.
			input.setText(preoperand);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	// ACTIONPREFORMED LISTENER FOR ALL THE MENU BUTTONS
	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventSource = e.getSource();
		if (eventSource == del) {
			ConsoleIf.append(computername + "~ $ View>Delete Utilities Pro Files");
			try {
				InOutMethods.delete();
			} catch (IOException e1) {
				log.append("\nDeletion Failed: IOException");
			}
		}
		if (eventSource == export) {
			display.append("\n" + computername + "~ $ File>Export ");
			try {
				InOutMethods.save();
			} catch (IOException e1) {
				log.append("\nExport Failed, IOException");
			}
		}
		if (eventSource == script) {
			display.append("\n" + computername + "~ $ File>Script");
			InOutMethods.script();
		}
		if (eventSource == mindterm) {
			display.append("\n" + computername + "~ $ File>Mindterm");
			try {
				InOutMethods.mindterm();
			} catch (IOException e1) {
				log.append("\nMindterm Download Failed: IOException");
			}
		}
		if (eventSource == purge) {
			display.append("\n" + computername + "~ $ Command>Purge");
			try {
				InOutMethods.purge();
			} catch (IOException e1) {
				log.append("\nPurge Failed: IOException");
			}
		}
		if (eventSource == debug) {
			ConsoleIf.append("\n" + computername + "~ $ Command>Debug");
			try {
				InOutMethods.saveLog();
			} catch (IOException e1) {
				log.append("\nBug JTextArea Export Failed: IOException");
			}
		}
		if (eventSource == info) {
			ConsoleIf.append(computername + "~ $ Command>System Readout");
			try {
				InfoMethods.info();
			} catch (InterruptedException e1) {
				log.append("\nInformation Not Exported: InterruptedException");
			} catch (IOException e1) {
				log.append("\nInformation Not Exported: IOException");
			}
		}
		if (eventSource == clear) {
			GraphicsMethods.clear();
		}
		if (eventSource == defaultCarat) {
			ConsoleIf.append(computername + "~ $ View>Snap to Bottom");
			GraphicsMethods.defaultCarat();
		}
		if (eventSource == logEnable) {
			ConsoleIf.append(computername + "~ $ View>Enable Log Tab");
			loggingSettings();
		}
		if (eventSource == metalInf) {
			ConsoleIf.append(computername + "~ $ View>Metal Interface");
			GraphicsMethods.saveConfig("CrossPlatformLAF");
		}
		if (eventSource == macIntrf) {
			ConsoleIf.append(computername + "~ $ View>System Interface");
			GraphicsMethods.saveConfig("Default");
		}
		if (eventSource == term) {
			ConsoleIf.append(computername + "~ $ Commands>Terminate Process");
			CoreMethods.terminate();
		}
		if (eventSource == about) {
			ConsoleIf.append(computername + "~ $ Help>About");
			InfoMethods.about();
		}
		if (eventSource == help) {
			ConsoleIf.append(computername + "~ $ Help>Help");
			try {
				CoreMethods.helpList();
			} catch (IOException e1) {
				log.append("\nHelp Invocation Failed: IOException");
			}
		}
		if (eventSource == changelog) {
			ConsoleIf.append(computername + "~ $ changelog");
			try {
				InfoMethods.changelog();
			} catch (IOException e1) {
				log.append("\nChangelog Invocation Failed: IOException");
			}
		}
		if (eventSource == updates) {
			ConsoleIf.append(computername + "~ $ Help>Updates");
			try {
				InOutMethods.update();
			} catch (IOException e1) {
				log.append("\nUtilities Pro Update FAILED: IOException");
			}
		}
	}
}
