package com.me.ifly6.addons;

import java.awt.BorderLayout;
import java.awt.Font;

import com.me.ifly6.*;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DebugMenu extends ConsoleIf {

	private static final long serialVersionUID = 1L;
	
	JFrame frame1 = new JFrame("iUtilities " + Parametres.version + ": DebugMenu");
	JPanel pane1 = new JPanel();
	static JTextArea display1 = new JTextArea();
	JScrollPane sp1 = new JScrollPane(display1);

	DebugMenu(){
		frame1.setBounds(50, 50, 670, 735);
		frame1.setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		pane1.setBackground(Color.BLACK);
		pane1.setForeground(Color.GREEN);
		pane1.setLayout(new BorderLayout());
		frame1.getContentPane().add(pane1);

		pane1.add(sp1);
		Font font = new Font("Monaco", 1, 12);
		display1.setBackground(Color.BLACK);
		display1.setForeground(Color.GREEN);
		display1.setEditable(false);
		display1.setFont(font);
		frame1.setVisible(true);
	}

	public static void execute() {
		new DebugMenu();
		while (true){
			String tmp = log.getText();
			display1.setText(tmp);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) { log("Plugin DebugMen Failed: Interrupted Exception"); }
		}
	}
}
