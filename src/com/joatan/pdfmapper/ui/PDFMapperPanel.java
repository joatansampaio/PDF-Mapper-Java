package com.joatan.pdfmapper.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class PDFMapperPanel extends JPanel {

	public static JTree destinationTree = new JTree();
	
	public PDFMapperPanel() {

		// Variables
		FileTree sourceTree = new FileTree();
		JButton browseBtn = new JButton("Browse Files");
		JButton executeBtn = new JButton("Execute Script");
		JButton addBtn = new JButton("Add to List");
		JButton removeAllBtn = new JButton("Remove All");
		JPanel btnPanel = new JPanel();

		
		destinationTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode()));

		// Button panel config
		btnPanel.setLayout(new GridLayout(0, 1));
		btnPanel.add(Box.createHorizontalStrut(10));
		btnPanel.add(addBtn);
		btnPanel.add(removeAllBtn);
		btnPanel.add(browseBtn);
		btnPanel.add(executeBtn);
		btnPanel.add(Box.createHorizontalStrut(10));

		Dimension buttonSize = new Dimension(200, 50);

		addBtn.setPreferredSize(buttonSize);
		removeAllBtn.setPreferredSize(buttonSize);
		executeBtn.setPreferredSize(buttonSize);
		browseBtn.setPreferredSize(buttonSize);

		// Sidebar config
		destinationTree.setPreferredSize(new Dimension(300, 700));
		destinationTree.setBackground(Color.WHITE);
		destinationTree.setLayout(new BoxLayout(destinationTree, BoxLayout.Y_AXIS));

		// Listeners
		browseBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Dialog dialog = new Dialog();
				dialog.open();
			}
		});

		executeBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		addBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				sourceTree.addSelectedNodesToDestinationTree();
			}
		});
		
		removeAllBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removeAllNodes(destinationTree);
			}
		});



		// Adding tree, browse button and the panel
		add(sourceTree);
		add(btnPanel);
		add(destinationTree);
	}
	
	public void removeAllNodes(JTree destinationTree) {
	    DefaultMutableTreeNode root = (DefaultMutableTreeNode)destinationTree.getModel().getRoot();
	    while (root.getChildCount() > 0) {
	        DefaultMutableTreeNode child = (DefaultMutableTreeNode)root.getChildAt(0);
	        child.removeFromParent();
	    }
	    ((DefaultTreeModel)destinationTree.getModel()).reload();
	}

}
