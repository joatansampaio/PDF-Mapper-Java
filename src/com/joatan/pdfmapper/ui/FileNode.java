package com.joatan.pdfmapper.ui;

import java.io.File;

public class FileNode {

	private File file;

	public FileNode(File file) {
		this.file = file;
	}

	//Making it mode readable filename for the tree
	@Override
	public String toString() {
		String name = file.getName();
		if (name.equals("")) {
			return file.getAbsolutePath();
		} else {
			return name;
		}
	}
}