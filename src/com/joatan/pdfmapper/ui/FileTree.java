package com.joatan.pdfmapper.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class FileTree extends JTree {
	/** Construct a FileTree */
	public FileTree() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));

		JScrollPane scrollpane = new JScrollPane();

		// Make a tree list with all the nodes, and make it a JTree
		File dir = new File(System.getProperty("user.dir"));
		JTree tree = new JTree();

		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Home") {
			{
				DefaultMutableTreeNode root = new DefaultMutableTreeNode(dir);
				createChildren(dir, root);
				add(root);

			}
		}));

		// Add a listener
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
				System.out.println("You selected " + node);
			}
		});

		// Lastly, put the JTree into a JScrollPane.
		scrollpane.getViewport().add(tree);
		add(BorderLayout.CENTER, scrollpane);
	}

	private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
		File[] files = fileRoot.listFiles();
		if (files == null)
			return;

		for (File file : files) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));

			String fileName = file.getName();
			String extension = "";
			int i = fileName.lastIndexOf('.');

			if (i > 0) {
				extension = fileName.substring(i + 1);
				if (extension.equals("pdf"))
					node.add(childNode);
			}

			if (file.isDirectory()) {
				createChildren(file, childNode);
			}
		}
	}

}