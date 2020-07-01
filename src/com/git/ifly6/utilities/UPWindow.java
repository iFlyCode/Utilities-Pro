package com.git.ifly6.utilities;

import com.git.ifly6.utilities.components.UPDirectoryManager;
import com.git.ifly6.utilities.components.UPHistory;
import com.git.ifly6.utilities.components.UPNotDirectoryException;
import com.git.ifly6.utilities.components.UPTabber;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import static com.git.ifly6.utilities.UtilitiesPro.FULL_VERSION;

public class UPWindow implements UPInteractable {

    private static final Logger LOGGER = Logger.getLogger(UPWindow.class.getName());
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

        textArea.setText(String.format("Welcome to %s\n========\n", FULL_VERSION));

        createMenus();
    }

    private void createMenus() {
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
