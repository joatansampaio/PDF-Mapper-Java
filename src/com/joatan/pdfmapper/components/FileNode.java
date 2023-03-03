package com.joatan.pdfmapper.components;

import java.io.File;


/**
 * @author Joatan Sampaio
 * @date 12/01/2022
 *
 */
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