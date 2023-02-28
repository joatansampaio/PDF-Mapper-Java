package com.joatan.pdfmapper.ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;

public class MainWindow {

	JFrame frame;
	JPanel panel;
	JButton browseBtn;
	public JTree fileTree;

	public void openWindow(String[] av) {

		frame = new JFrame("PDF Mapper");
		frame.setSize(700, 700);
		frame.setForeground(Color.black);
		frame.setBackground(Color.lightGray);

		panel = new PDFMapperPanel();
		
		frame.add(panel); 

		frame.setLocationRelativeTo(null); // Centralizing frame to the screen
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
