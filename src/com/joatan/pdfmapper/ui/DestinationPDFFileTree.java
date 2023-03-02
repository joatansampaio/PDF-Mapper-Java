package com.joatan.pdfmapper.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class DestinationPDFFileTree extends JTree {

	ArrayList<File> treePDFFiles;

	public DestinationPDFFileTree() {
		this.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Selected PDFs")));

		this.setPreferredSize(new Dimension(300, 700));
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTree tree = (JTree) e.getSource();
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					if (selectedNode != null) {
						// Remove the selected node from the destination tree
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectedNode.getParent();
						if (parent != null) {
							parent.remove(selectedNode);
							((DefaultTreeModel) tree.getModel()).reload(parent);
						}
					}
				}
			}
		});
	}

	public void removeAllNodes() {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.getModel().getRoot();
		while (root.getChildCount() > 0) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(0);
			child.removeFromParent();
		}
		((DefaultTreeModel) this.getModel()).reload();
	}

	public DefaultMutableTreeNode getRoot() {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.getModel().getRoot();
		return root;
	}

	public void setSelectedFiles(ArrayList<File> selectedFiles) {
		this.treePDFFiles = selectedFiles;
	}

	public ArrayList<File> getSelectedFiles() {
		return treePDFFiles;
	}

}
