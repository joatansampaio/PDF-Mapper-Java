package com.joatan.pdfmapper.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.joatan.pdfmapper.components.FileNode;

/**
 * @author Joatan Sampaio
 * @date 12/01/2022
 *
 */
@SuppressWarnings("serial")
public class SourceTree extends JTree {

	public SourceTree() {
		super();
		setLayout(new BorderLayout());
		
		setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Browse the folder with the PDFs")));

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addSelectedNodesToDestinationTree();
				}
			}
		});
		
	}

	public void populateTree(File src) {
		setModel(new DefaultTreeModel(new DefaultMutableTreeNode(".") {
			{
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(src.getName());

				File[] listOfFiles = src.listFiles();
				if (listOfFiles != null) {
					for (File file : listOfFiles) {
						DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));

						String fileName = file.getName();
						String fileExtension = "";

						int periodIndex = fileName.lastIndexOf('.');

						if (periodIndex > 0) {
							fileExtension = fileName.substring(periodIndex + 1);
							if (fileExtension.equals("pdf")) {
								root.add(childNode);
							}
						}
					}

					add(root);
				}
			}
		}));
	}

	public void addSelectedNodesToDestinationTree() {
		TreePath[] selectedFiles = getSelectionPaths();
		if (selectedFiles != null) {
			for (TreePath path : selectedFiles) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
				if (!nodeExists(selectedNode,
						(DefaultMutableTreeNode) MainWindow.mainPanel.getDestinationTree().getModel().getRoot())) {
					DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(selectedNode.getUserObject());
					((DefaultMutableTreeNode) MainWindow.mainPanel.getDestinationTree().getModel().getRoot()).add(newChild);
					((DefaultTreeModel) MainWindow.mainPanel.getDestinationTree().getModel()).reload();
				}
			}
		}
	}

	// Checking if the node already exists in the destination tree
	private boolean nodeExists(DefaultMutableTreeNode node, DefaultMutableTreeNode root) {
		Enumeration<?> e = root.depthFirstEnumeration();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
			Object userObject = n.getUserObject();
			if (userObject != null && userObject.equals(node.getUserObject())) {
				return true;
			}
		}
		return false;
	}

}