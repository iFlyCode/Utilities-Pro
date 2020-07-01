package com.git.ifly6.utilities;

import com.git.ifly6.utilities.components.UPHistory;
import com.git.ifly6.utilities.components.UPTabber;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UPWindow implements UPPrintable {
    private JTextField inputField;
    private JTextArea textArea;
    private JPanel panel;

    private UPHistory history;

    public UPWindow() {
        panel.setMinimumSize(new Dimension(400, 400));
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER)
                    UPExecutor.getInstance().execute(inputField.getText(), UPWindow.this);
                else if (keyCode == KeyEvent.VK_UP)
                    inputField.setText(history.step(UPHistory.BACK));
                else if (keyCode == KeyEvent.VK_DOWN)
                    inputField.setText(history.step(UPHistory.FORWARD));
                else if (keyCode == KeyEvent.VK_TAB)
                    inputField.setText(UPTabber.tabComplete(inputField.getText()));
            }
        });

        createMenus();
    }

    private void createMenus() {
    }

    public void append(String s) {
        if (!textArea.getText().endsWith("\n")) s = "\n" + s;
        textArea.append(s);
    }

    @Override
    public void out(String s) {
        this.append(s);
    }

    @Override
    public void log(String s) {
        this.append(s);
    }
}
