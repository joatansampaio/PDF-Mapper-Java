package com.joatan.pdfmapper.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

		fileTree = new FileTree(new File("."));
		fileTree.setPreferredSize(new Dimension(400, 400));

		panel = new JPanel();

		panel.setSize(400, 400);
		panel.add(fileTree);

		browseBtn = new JButton("Browse");

		browseBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Dialog dialog = new Dialog();
				dialog.open();
			}
		});

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		panel.add(browseBtn);
		frame.add(panel);

		frame.setLocationRelativeTo(null); // Center
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
