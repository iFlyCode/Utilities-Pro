package com.git.ifly6.utilities;

import com.git.ifly6.iflyLibrary.swing.IflyFileChooser;
import com.git.ifly6.utilities.components.UPDirectoryManager;
import com.git.ifly6.utilities.components.UPHistory;
import com.git.ifly6.utilities.components.UPLinks;
import com.git.ifly6.utilities.components.UPNotDirectoryException;
import com.git.ifly6.utilities.components.UPScripts;
import com.git.ifly6.utilities.components.UPTabber;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
import java.awt.FileDialog;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.git.ifly6.utilities.UtilitiesPro.APP_SUPPORT;
import static com.git.ifly6.utilities.UtilitiesPro.COMPUTER_NAME;
import static com.git.ifly6.utilities.UtilitiesPro.FULL_VERSION;

@SuppressWarnings("DuplicatedCode")
public class UPWindow implements UPInteractable {

    private static final Logger LOGGER = Logger.getLogger(UPWindow.class.getName());
    private static final String HEADER = String.format("Welcome to %s\n========\n\n", FULL_VERSION);

    private JFrame frame = null;
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
                String input = inputField.getText();
                // LOGGER.info(String.format("Key released: %d", keyCode));

                if (keyCode == KeyEvent.VK_ENTER) {
                    // formatting to add new line
                    if (!UPWindow.this.textArea.getText().endsWith("\n\n"))
                        UPWindow.this.append("\n");

                    loopback(input);
                    inputField.setText("");

                    // parse
                    if (input.startsWith("/"))
                        UPInternalCommand
                                .parseCommand(input.replaceFirst("/", ""))
                                .ifPresent(i -> i.execute(UPWindow.this));
                    else {
                        UPWindow.this.history.add(input);
                        UPExecutor.getInstance().execute(input, UPWindow.this);
                    }

                } else if (keyCode == KeyEvent.VK_UP)
                    inputField.setText(history.step(UPHistory.BACK));

                else if (keyCode == KeyEvent.VK_DOWN)
                    inputField.setText(history.step(UPHistory.FORWARD));

                else if (keyCode == KeyEvent.VK_TAB) {
                    loopback(input);
                    inputField.setText(UPTabber.tabComplete(
                            inputField.getText(),
                            UPWindow.this.cdManager.getPath(),
                            UPWindow.this
                    ));
                }
            }

            /**
             * Loops back input to the text area terminal
             * @param input to loop back
             */
            private void loopback(String input) {
                UPWindow.this.out(String.format("%s %s $ %s", // command loopback
                        COMPUTER_NAME,
                        UPWindow.this.getDirectory().getName(),
                        input));
            }
        });

        textArea.setText(HEADER);

        createMenus();
    }

    public UPWindow(JFrame f) {
        this();
        this.frame = f;
    }

    protected JMenuBar createMenus() {
        JMenuBar menuBar = new JMenuBar();

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmDeleteUtilitiesProFolder = new JMenuItem("Delete Utilities Pro Folder");
        mntmDeleteUtilitiesProFolder.addActionListener(e -> {
            try {
                Files.list(APP_SUPPORT)
                        .forEach(f -> {
                            try {
                                Files.deleteIfExists(f);
                            } catch (IOException ex) {
                                this.out("Failed to list delete file " + f.getFileName());
                                ex.printStackTrace();
                            }
                        });
            } catch (IOException ex) {
                this.out("Failed to list contents of Utilities Pro folder");
                ex.printStackTrace();
            }
        });
        mnFile.add(mntmDeleteUtilitiesProFolder);

        mnFile.addSeparator();

        JMenuItem mntmExportConsole = new JMenuItem("Export Console");
        mntmExportConsole.addActionListener(e -> {
            List<String> l = Arrays.asList(textArea.getText().split("(\\r\\n|\\r|\\n)"));
            IflyFileChooser.showFileChooser(
                    frame,
                    APP_SUPPORT,
                    FileDialog.SAVE
            ).ifPresent(f -> {
                try {
                    Files.write(f, l);
                } catch (IOException ex) {
                    this.out("Failed to write console contents to file: " + f.getFileName());
                    ex.printStackTrace();
                }
            });
        });
        mnFile.add(mntmExportConsole);

        JMenuItem exportHistory = new JMenuItem("Export History");
        exportHistory.addActionListener(e ->
                IflyFileChooser.showFileChooser(
                        frame,
                        APP_SUPPORT,
                        FileDialog.SAVE
                ).ifPresent(path -> history.toFile(path, this)));
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
        mntmClearConsole.addActionListener(e -> UPInternalCommand.CLEAR.execute(this));
        mnEdit.add(mntmClearConsole);

        JMenu mnScripts = new JMenu("Scripts");
        menuBar.add(mnScripts);

        JMenuItem mntmRestartAirport = new JMenuItem("Restart Airport");
        mntmRestartAirport.addActionListener(e -> UPScripts.restartWireless(this));
        mnScripts.add(mntmRestartAirport);

        JMenuItem mntmFinderChange = new JMenuItem("Change Finder Options");
        mntmFinderChange.addActionListener(arg0 -> {
            String[] options = {"Cancel", "Hidden", "Visible"};
            String s = options[
                    JOptionPane.showOptionDialog(panel.getParent(),
                            "Select visibility of the Finder quit button and hidden files.",
                            "Utilities Pro", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
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
        mntmTerminateUtilitiesPro.addActionListener(event -> UPExecutor.getInstance().terminateRunning());
        mntmTerminateUtilitiesPro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.META_MASK));
        mnCommand.add(mntmTerminateUtilitiesPro);

        JMenuItem mntmTerminateArbitraryProcess = new JMenuItem("Terminate Process by PID");
        mntmTerminateArbitraryProcess.addActionListener(e -> {
            int pid = -1; // todo implement chooser dialog using UPPsaxParser
            UPScripts.killProcess(pid, this);
        });
        mnCommand.add(mntmTerminateArbitraryProcess);

        JMenu mnHelp = new JMenu("Help");
        mnHelp.addActionListener(e -> UPLinks.openLink(UPLinks.HELP, this));
        menuBar.add(mnHelp);

        JMenuItem mntmAbout = new JMenuItem("About");
        mntmAbout.addActionListener(e -> UPLinks.openLink(UPLinks.README, this));
        mnHelp.add(mntmAbout);

        JMenuItem mntmHelp = new JMenuItem("Utilities Pro Help");
        mntmHelp.addActionListener(e -> UPLinks.openLink(UPLinks.GITHUB, this));
        mnHelp.add(mntmHelp);

        JMenuItem mntmBashHelp = new JMenuItem("Bash Help");
        mntmBashHelp.addActionListener(e -> UPLinks.openLink(UPLinks.BASH, this));
        mnHelp.add(mntmBashHelp);

        return menuBar;
    }

    public void append(String s) {
        if (!textArea.getText().endsWith("\n")) s = "\n" + s; // pre-pend new lines as necessary
        textArea.append(s);
        textArea.setCaretPosition(textArea.getText().length()); // move to bottom
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
     * Internal commands that can be executed.
     */
    @SuppressWarnings("unused")
    public enum UPInternalCommand {
        /* Suppress unused warnings because they are invoked by parsing and not explicitly. */

        HELP {
            @Override
            public void execute(UPWindow i) {
                final String helpText = "Valid internal commands are:\n\n" +
                        Arrays.stream(UPInternalCommand.values())
                                .map(s -> " * " + s)
                                .collect(Collectors.joining("\n"));
                i.out(helpText);
            }
        },

        OPEN_APP_SUPPORT {
            @Override
            public void execute(UPWindow i) {
                LOGGER.info("App support: " + APP_SUPPORT);
                UPExecutor.getInstance().execute(String.format(
                        "open %s",
                        UPDirectoryManager.escapeSpaces(APP_SUPPORT.toString() + "/")
                        // ifly6 (2020-07-03) i'm aware it's hacky, not sure how to do it properly
                ), i);
            }
        },

        EXPORT_LOG {
            @Override
            public void execute(UPWindow i) {
                i.history.toFile(APP_SUPPORT.resolve("console-export.txt"), i);
            }
        },

        CLEAR {
            @Override
            public void execute(UPWindow i) {
                i.textArea.setText(HEADER);
                i.textArea.append("");
            }
        };

        public abstract void execute(UPWindow i);

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        public static Optional<UPInternalCommand> parseCommand(String s) {
            return Arrays.stream(UPInternalCommand.values())
                    .filter(c -> c.toString().equals(s.toLowerCase())) // see supra, always c.toString is lower cased
                    .findFirst(); // ifPresent -> otherwise do nothing
        }
    }
}
