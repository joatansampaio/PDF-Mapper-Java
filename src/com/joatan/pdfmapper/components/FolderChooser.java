package com.joatan.pdfmapper.components;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * @author Joatan Sampaio
 * @date 12/01/2022
 *
 */
public class FolderChooser {

	public String selectFolder() {
		JFrame frame = new JFrame();
		JFileChooser folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow selection of directories
		
		String folderPath = null;

		int result = folderChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION)
			folderPath = folderChooser.getSelectedFile().getAbsolutePath();
		if (result == JFileChooser.CANCEL_OPTION)
			folderPath = "";

		return folderPath;
	}

}