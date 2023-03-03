package com.joatan.pdfmapper.ui;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * @author Joatan Sampaio
 * @date 12/01/2022
 *
 */
public class MainWindow {

	public static MainPanel mainPanel = new MainPanel();

	public void openWindow() {

		JFrame mainWindowFrame;

		mainWindowFrame = new JFrame("PDF Mapper");
		mainWindowFrame.setSize(800, 800);

		mainWindowFrame.add(mainPanel);
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}

		mainWindowFrame.setLocationRelativeTo(null); // Centralizing frame to the screen
		mainWindowFrame.pack();
		mainWindowFrame.setVisible(true);
		mainWindowFrame.setResizable(false);
		mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
