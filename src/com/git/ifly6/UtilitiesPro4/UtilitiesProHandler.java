package com.git.ifly6.UtilitiesPro4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UtilitiesProHandler {

	// Menu Bar
	@FXML private MenuBar menuBar;
	@FXML private MenuItem openConfig;
	@FXML private MenuItem deleteConfig;
	@FXML private MenuItem exportConsole;
	@FXML private MenuItem exportLog;

	// Input and Output
	@FXML private TextField inputField;
	@FXML private TextArea outText;
	@FXML private TextArea logText;

	protected void initialize() {
		outText.setText(null);
		logText.setText(null);
	}

	protected void openConfig(ActionEvent event) {

	}

	protected void deleteConfig(ActionEvent event) {

	}

	protected void exportConsole(ActionEvent event) {

	}

	protected void exportLog(ActionEvent event) {

	}
}
