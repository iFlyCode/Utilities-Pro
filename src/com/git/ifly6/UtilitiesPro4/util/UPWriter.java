package com.git.ifly6.UtilitiesPro4.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class UPWriter {

	PrintWriter writer;

	public UPWriter(File file) throws FileNotFoundException {
		writer = new PrintWriter(file);
	}

	public UPWriter(String pathToFile) throws FileNotFoundException {
		writer = new PrintWriter(new File(pathToFile));
	}

	public void setMode(UPWriterMode mode) {

	}

	public void write() {

	}
}

enum UPWriterMode {
	CONSOLE, LOG;
}
