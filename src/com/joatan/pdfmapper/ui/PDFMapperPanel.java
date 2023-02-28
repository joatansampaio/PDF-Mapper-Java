package com.joatan.pdfmapper.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class PDFMapperPanel extends JPanel{

	public PDFMapperPanel() {
		FileTree fileTree = new FileTree();
		JButton browseBtn = new JButton ("       Browse      ");
		JButton executeBtn = new JButton("Execute Script");	
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		JScrollPane sidebar = new JScrollPane();
		
		sidebar.setPreferredSize(new Dimension(400,600));
		sidebar.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Testing...");
		
		browseBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Dialog dialog = new Dialog();
				dialog.open();
			}
		});

		btnPanel.add(browseBtn);
		btnPanel.add(executeBtn);
		
		sidebar.getViewport().add(label);
		//Adding tree, browse button and the panel
		add(fileTree); 
		add(btnPanel); 
		add(sidebar);
	}
	
}
