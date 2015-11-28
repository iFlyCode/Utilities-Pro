/* Copyright (c) 2015 Kevin Wong
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */

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
