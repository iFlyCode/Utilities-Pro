package com.git.ifly6.UtilitiesPro4.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.TextArea;

public class UPLogger {

	private TextArea outText;
	private TextArea logText;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public UPLogger(TextArea outPane, TextArea logPane) {
		outText = outPane;
		logText = logPane;
	}

	public void log(String input) {
		logText.appendText("[" + currentTime() + "] " + input + "\n");
	}

	public void out(String input) {
		outText.appendText(input + "\n");
	}

	private String currentTime() {
		Date date = new Date();
		return dateFormat.format(date);
	}
}
