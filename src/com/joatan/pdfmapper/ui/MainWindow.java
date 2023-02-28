package com.joatan.pdfmapper.ui;

import java.awt.Color;
import javax.swing.JFrame;

public class MainWindow {

	public static PDFMapperPanel mainPanel = new PDFMapperPanel();
	
	public void openWindow(String[] av) {
		
		JFrame mainWindowFrame;

		mainWindowFrame = new JFrame("PDF Mapper");
		mainWindowFrame.setSize(800, 800);
		mainWindowFrame.setForeground(Color.black);
		mainWindowFrame.setBackground(Color.lightGray);
		
		mainWindowFrame.add(mainPanel); 

		mainWindowFrame.setLocationRelativeTo(null); // Centralizing frame to the screen
		mainWindowFrame.pack();
		mainWindowFrame.setVisible(true);
		mainWindowFrame.setResizable(false);
		mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
