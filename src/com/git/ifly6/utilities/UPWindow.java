package com.git.ifly6.utilities;

import com.git.ifly6.utilities.components.UPDirectoryManager;
import com.git.ifly6.utilities.components.UPHistory;
import com.git.ifly6.utilities.components.UPNotDirectoryException;
import com.git.ifly6.utilities.components.UPScripts;
import com.git.ifly6.utilities.components.UPTabber;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import static com.git.ifly6.utilities.UtilitiesPro.FULL_VERSION;

public class UPWindow implements UPInteractable {

    private static final Logger LOGGER = Logger.getLogger(UPWindow.class.getName());
    private static final String HEADER = String.format("Welcome to %s\n========\n", FULL_VERSION);
    public JPanel panel;

    private JTextField inputField;
    private JTextArea textArea;

    private UPHistory history = new UPHistory();
    private UPDirectoryManager cdManager = new UPDirectoryManager();

    public UPWindow() {
        panel.setMinimumSize(new Dimension(400, 400));
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        inputField.setFocusTraversalKeysEnabled(false);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) { // MUST use keyReleased, not keyTyped for enter, tab, etc.
                int keyCode = e.getKeyCode();
                // LOGGER.info(String.format("Key released: %d", keyCode));

                if (keyCode == KeyEvent.VK_ENTER) {
                    String input = inputField.getText();
                    if (input.startsWith("/")) {
                        Optional<UPInternalCommand> c = UPInternalCommand
                                .parseCommand(input.replaceFirst("/", ""));
                        c.ifPresent(i -> i.execute(UPWindow.this));

                    } else {
                        UPWindow.this.history.add(input);
                        inputField.setText("");
                        UPExecutor.getInstance().execute(input, UPWindow.this);
                    }

                } else if (keyCode == KeyEvent.VK_UP)
                    inputField.setText(history.step(UPHistory.BACK));

                else if (keyCode == KeyEvent.VK_DOWN)
                    inputField.setText(history.step(UPHistory.FORWARD));

                else if (keyCode == KeyEvent.VK_TAB)
                    inputField.setText(UPTabber.tabComplete(
                            inputField.getText(),
                            UPWindow.this.cdManager.getPath()
                    ));
            }
        });

        textArea.setText(HEADER);

        createMenus();
    }

    protected JMenuBar createMenus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmDeleteUtilitiesProFolder = new JMenuItem("Delete Utilities Pro Folder");
        mntmDeleteUtilitiesProFolder.addActionListener(e -> {
        });
        mnFile.add(mntmDeleteUtilitiesProFolder);

        mnFile.addSeparator();

        JMenuItem mntmExportConsole = new JMenuItem("Export Console\n");
        mntmExportConsole.addActionListener(e -> {
        });
        mnFile.add(mntmExportConsole);

        JMenuItem exportHistory = new JMenuItem("Export History");
        exportHistory.addActionListener(e -> {
        });
        mnFile.add(exportHistory);

        JMenu mnEdit = new JMenu("Edit");
        menuBar.add(mnEdit);

        JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
        mntmCut.setText("Cut");
        mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.META_MASK));
        mnEdit.add(mntmCut);

        JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
        mntmCopy.setText("Copy");
        mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_MASK));
        mnEdit.add(mntmCopy);

        JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
        mntmPaste.setText("Paste");
        mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_MASK));
        mnEdit.add(mntmPaste);

        mnEdit.addSeparator();

        JMenuItem mntmClearConsole = new JMenuItem("Clear Console");
        mntmClearConsole.addActionListener(e -> {
            textArea.setText(HEADER);
            textArea.append("");
        });
        mnEdit.add(mntmClearConsole);

        JMenu mnScripts = new JMenu("Scripts");
        menuBar.add(mnScripts);

        JMenuItem mntmPurge = new JMenuItem("Purge Memory");
        mntmPurge.addActionListener(e -> {
        });
        mnScripts.add(mntmPurge);

        JMenuItem mntmRestartAirport = new JMenuItem("Restart Airport");
        mntmRestartAirport.addActionListener(e -> {
        });
        mnScripts.add(mntmRestartAirport);

        JMenuItem mntmFinderChange = new JMenuItem("Change Finder Options");
        mntmFinderChange.addActionListener(arg0 -> {
            String[] options = {"Cancel", "Hidden", "Visible"};
            String s = options[
                    JOptionPane.showOptionDialog(panel.getParent(),
                            "Select an option to change the visibility of the Finder Quit opton and hidden files to.",
                            "Utilities Pro", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[0])
                    ];

            switch (s) {
                case "Hidden":
                    UPScripts.configureFinder(false, this);
                case "Visible":
                    UPScripts.configureFinder(true, this);
            }
        });
        mnScripts.add(mntmFinderChange);

        JMenu mnCommand = new JMenu("Command");
        menuBar.add(mnCommand);

        JMenuItem mntmTerminateUtilitiesPro = new JMenuItem("Terminate Utilities Pro Process");
        mntmTerminateUtilitiesPro.addActionListener(e -> {
        });
        mntmTerminateUtilitiesPro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.META_MASK));
        mnCommand.add(mntmTerminateUtilitiesPro);

        JMenuItem mntmTerminateArbitraryProcess = new JMenuItem("Terminate Process by PID");
        mntmTerminateArbitraryProcess.addActionListener(e -> {
        });
        mnCommand.add(mntmTerminateArbitraryProcess);

        JMenu mnHelp = new JMenu("Help");
        menuBar.add(mnHelp);

        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener(e -> {
        });
        mnHelp.add(mntmAbout);

        JMenuItem mntmHelp = new JMenuItem("Utilities Pro Help");
        mntmHelp.addActionListener(e -> {
        });
        mnHelp.add(mntmHelp);

        JMenuItem mntmBashHelp = new JMenuItem("Bash Help");
        mntmBashHelp.addActionListener(e -> {
        });
        mnHelp.add(mntmBashHelp);

        return menuBar;
    }

    public void append(String s) {
        if (!textArea.getText().endsWith("\n")) s = "\n" + s;
        textArea.append(s);
        textArea.setCaretPosition(textArea.getText().length());
    }

    @Override
    public void out(String s) {
        this.append(s);
    }

    @Override
    public void log(String s) {
        this.append(s);
    }

    @Override
    public void changeDirectory(String p) {
        try {
            cdManager.appendPath(p);
        } catch (UPNotDirectoryException e) {
            e.printStackTrace();
            append(String.format("Cannot change directory to %s as it is not a directory", p));
        }
    }

    @Override
    public File getDirectory() {
        return cdManager.getPath().toFile();
    }

    /**
     * Internal commands that can be executed
     */
    enum UPInternalCommand {
        EXPORT_LOG {
            @Override
            public void execute(UPWindow i) {

            }
        },

        CLEAR {
            @Override
            public void execute(UPWindow i) {

            }
        };

        public abstract void execute(UPWindow i);

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        public static Optional<UPInternalCommand> parseCommand(String s) {
            return Arrays.stream(UPInternalCommand.values())
                    .filter(c -> c.toString().equals(s))
                    .findFirst(); // ifPresent -> otherwise do nothing
        }
    }
}
