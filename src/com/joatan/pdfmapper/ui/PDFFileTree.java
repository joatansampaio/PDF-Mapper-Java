package com.joatan.pdfmapper.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class PDFFileTree extends JTree {

	JTree sourceTree;
	String folderSrc = "";
	ArrayList<File> treePDFFiles;

	public PDFFileTree() {
		treePDFFiles = new ArrayList<>();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 700));

		JScrollPane scrollpane = new JScrollPane();

		// Make a tree list with all the nodes, and make it a JTree
		sourceTree = new JTree();

		// Initial model for standard folder.
		sourceTree.setModel(null);
		
		sourceTree.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addSelectedNodesToDestinationTree();
				}
			}
		});

		scrollpane.getViewport().add(sourceTree);
		add(BorderLayout.CENTER, scrollpane);
	}
	
	public void populateTree(File src) {
		sourceTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(".") {
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
								treePDFFiles.add(file);
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
		TreePath[] selectedFiles = sourceTree.getSelectionPaths();
		if (selectedFiles != null) {
			for (TreePath path : selectedFiles) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
				if (!nodeExists(selectedNode,
						(DefaultMutableTreeNode) MainPanel.getDestinationTree().getModel().getRoot())) {
					DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(selectedNode.getUserObject());
					((DefaultMutableTreeNode) MainPanel.getDestinationTree().getModel().getRoot()).add(newChild);
					((DefaultTreeModel) MainPanel.getDestinationTree().getModel()).reload();
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
	
	public void resetTreeArray() {
		treePDFFiles.clear();
	}
	
	public void setTreeFiles(ArrayList<File> selectedFiles) {
		this.treePDFFiles = selectedFiles;
	}
	
	public ArrayList<File> getTreeFiles() {
		return treePDFFiles;
	}
	
	public void printTreeArray() {
		for(File file : treePDFFiles)
			System.out.println(file.getAbsolutePath());
	}
}