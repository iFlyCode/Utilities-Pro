package ifly6.utilitiesPro4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UtilitiesPro4 {

	private JFrame frame;
	private HashMap<String, String> configMap = new HashMap<>();

	public UtilitiesPro4() {
		// Load configuration into hashMap
		// Deal with configuration

		initialise();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UtilitiesPro4 window = new UtilitiesPro4();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialise() {
		frame = new JFrame("Utilities Pro 4");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JTextArea textArea = new JTextArea();
		scrollPane.add(textArea, BorderLayout.NORTH);
	}

}
