package com.joatan.pdfmapper.ui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FolderChooser {

	public String selectFolder() {
		JFrame frame = new JFrame();
		String folderPath = null;
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow selection of directories

		int result = chooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			folderPath = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("Selected folder path: " + folderPath);
			// do something with the selected folder path
		}

		return folderPath;
	}

}