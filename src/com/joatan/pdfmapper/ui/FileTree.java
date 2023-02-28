package com.joatan.pdfmapper.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Enumeration;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class FileTree extends JTree {

	DefaultMutableTreeNode node;
	TreeSelectionEvent treeSelectionEvent;
	JTree tree;

	public FileTree() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 700));

		JScrollPane scrollpane = new JScrollPane();

		// Make a tree list with all the nodes, and make it a JTree
		File dir = new File(System.getProperty("user.dir"));
		tree = new JTree();

		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(".") {
			{
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(dir);
				createChildren(dir, root);
				add(root);
			}
		}));

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					System.out.println("Here");
					JTree source = (JTree) e.getSource();
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) source
							.getLastSelectedPathComponent();
					if (selectedNode != null && selectedNode.isLeaf()) {
						JTree dTree = PDFMapperPanel.destinationTree;
						DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(selectedNode.getUserObject());
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) dTree.getLastSelectedPathComponent();

						DefaultMutableTreeNode root = (DefaultMutableTreeNode) dTree.getModel().getRoot();
						boolean exists = nodeExists(selectedNode, root);
						if (!exists) {
							if (parent == null) {
								parent = (DefaultMutableTreeNode) dTree.getModel().getRoot();
							}
							parent.add(newNode);
							((DefaultTreeModel) dTree.getModel()).reload(parent);
						}
					}
				}
			}
		});
		
		PDFMapperPanel.destinationTree.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) {
		            JTree tree = (JTree)e.getSource();
		            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		            if (selectedNode != null) {
		                // Remove the selected node from the destination tree
		                DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
		                if (parent != null) {
		                    parent.remove(selectedNode);
		                    ((DefaultTreeModel)tree.getModel()).reload(parent);
		                }
		            }
		        }
		    }
		});
		
		//tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

		tree.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            addSelectedNodesToDestinationTree();
		        }
		    }
		});

		scrollpane.getViewport().add(tree);
		add(BorderLayout.CENTER, scrollpane);
	}
	
	public void addSelectedNodesToDestinationTree(){
		TreePath[] selectedPaths = tree.getSelectionPaths();
        if (selectedPaths != null) {
            for (TreePath path : selectedPaths) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();
                if (!nodeExists(selectedNode, (DefaultMutableTreeNode)PDFMapperPanel.destinationTree.getModel().getRoot())) {
                    DefaultMutableTreeNode newChild = new DefaultMutableTreeNode(selectedNode.getUserObject());
                    ((DefaultMutableTreeNode)PDFMapperPanel.destinationTree.getModel().getRoot()).add(newChild);
                    ((DefaultTreeModel)PDFMapperPanel.destinationTree.getModel()).reload();
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

	private void createChildren(File fileRoot, DefaultMutableTreeNode mainNode) {
		File[] files = fileRoot.listFiles();
		if (files == null)
			return;

		for (File file : files) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));

			String fileName = file.getName();
			String fileExtension = "";

			int periodIndex = fileName.lastIndexOf('.');

			if (periodIndex > 0) {
				fileExtension = fileName.substring(periodIndex + 1);
				if (fileExtension.equals("pdf"))
					mainNode.add(childNode);
			}

			if (file.isDirectory()) {
				createChildren(file, childNode);
			}
		}
	}

}